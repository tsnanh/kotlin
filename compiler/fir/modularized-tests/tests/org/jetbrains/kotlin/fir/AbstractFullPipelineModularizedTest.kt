/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir

import org.jetbrains.kotlin.cli.common.*
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.fir.scopes.ProcessorAction
import java.io.FileOutputStream
import java.io.PrintStream
import java.nio.file.Files

abstract class AbstractFullPipelineModularizedTest : AbstractModularizedTest() {

    protected data class CumulativeTime(
        val gcInfo: Map<String, GCInfo>,
        val components: Map<String, Long>,
        val files: Int,
        val lines: Int
    ) {
        constructor() : this(emptyMap(), emptyMap(), 0, 0)

        operator fun plus(other: CumulativeTime): CumulativeTime {
            return CumulativeTime(
                (gcInfo.values + other.gcInfo.values).groupingBy { it.name }.reduce { key, accumulator, element ->
                    GCInfo(key, accumulator.gcTime + element.gcTime, accumulator.collections + element.collections)
                },
                (components.toList() + other.components.toList()).groupingBy { (name, _) -> name }.fold(0L) { a, b -> a + b.second },
                files + other.files,
                lines + other.lines
            )
        }

        fun totalTime() = components.values.sum()
    }

    protected lateinit var totalPassResult: CumulativeTime

    override fun beforePass(pass: Int) {
        totalPassResult = CumulativeTime()
    }

    protected open fun formatReport(stream: PrintStream, finalReport: Boolean) {
        val total = totalPassResult
        var totalGcTimeMs = 0L
        var totalGcCount = 0L
        printTable(stream) {
            row("Name", "Time", "Count")
            separator()
            fun gcRow(name: String, timeMs: Long, count: Long) {
                row {
                    cell(name, align = LEFT)
                    timeCell(timeMs, inputUnit = TableTimeUnit.MS)
                    cell(count.toString())
                }
            }
            for (measurement in total.gcInfo.values) {
                totalGcTimeMs += measurement.gcTime
                totalGcCount += measurement.collections
                gcRow(measurement.name, measurement.gcTime, measurement.collections)
            }
            separator()
            gcRow("Total", totalGcTimeMs, totalGcCount)

        }

        printTable(stream) {
            row("Phase", "Time", "Files", "L/S")
            separator()

            fun phase(name: String, timeMs: Long, files: Int, lines: Int) {
                row {
                    cell(name, align = LEFT)
                    timeCell(timeMs, inputUnit = TableTimeUnit.MS)
                    cell(files.toString())
                    linePerSecondCell(lines, timeMs, timeUnit = TableTimeUnit.MS)
                }
            }
            for (component in total.components) {
                phase(component.key, component.value, total.files, total.lines)
            }

            separator()
            phase("Total", total.totalTime(), total.files, total.lines)
        }

    }

    private fun configureBaseArguments(args: K2JVMCompilerArguments, moduleData: ModuleData) {
        args.reportPerf = true
        args.jvmTarget = "1.8"
        args.classpath = moduleData.classpath.joinToString(separator = ":") { it.absolutePath }
        args.javaSourceRoots = moduleData.javaSourceRoots.map { it.absolutePath }.toTypedArray()
        args.allowKotlinPackage = true
        args.freeArgs = moduleData.sources.map { it.absolutePath }
        val tmp = Files.createTempDirectory("compile-output")
        args.destination = tmp.toAbsolutePath().toFile().toString()
        args.friendPaths = moduleData.friendDirs.map { it.canonicalPath }.toTypedArray()
    }

    abstract fun configureArguments(args: K2JVMCompilerArguments, moduleData: ModuleData)

    protected abstract fun handleResult(result: ExitCode, moduleData: ModuleData, collector: TestMessageCollector): ProcessorAction

    override fun processModule(moduleData: ModuleData): ProcessorAction {
        val compiler = K2JVMCompiler()
        val args = compiler.createArguments()
        configureBaseArguments(args, moduleData)
        configureArguments(args, moduleData)

        val manager = CompilerPerformanceManager()
        val services = Services.Builder().register(CommonCompilerPerformanceManager::class.java, manager).build()
        val collector = TestMessageCollector()
        val result = try {
            compiler.exec(collector, services, args)
        } catch (e: Exception) {
            e.printStackTrace()
            ExitCode.INTERNAL_ERROR
        }
        val resultTime = manager.reportCumulativeTime()

        if (result == ExitCode.OK) {
            totalPassResult += resultTime
        }

        return handleResult(result, moduleData, collector)
    }

    protected fun createReport(finalReport: Boolean) {
        formatReport(System.out, finalReport)

        PrintStream(
            FileOutputStream(
                reportDir().resolve("report-$reportDateStr.log"),
                true
            )
        ).use { stream ->
            formatReport(stream, finalReport)
            stream.println()
            stream.println()
        }
    }


    private inner class CompilerPerformanceManager : CommonCompilerPerformanceManager("Modularized test performance manager") {

        fun reportCumulativeTime(): CumulativeTime {
            val gcInfo = measurements.filterIsInstance<GarbageCollectionMeasurement>()
                .associate { it.garbageCollectionKind to GCInfo(it.garbageCollectionKind, it.milliseconds, it.count) }

            val analysisMeasurement = measurements.filterIsInstance<CodeAnalysisMeasurement>().firstOrNull()
            val initMeasurement = measurements.filterIsInstance<CompilerInitializationMeasurement>().firstOrNull()
            val irMeasurements = measurements.filterIsInstance<IRMeasurement>()

            @OptIn(ExperimentalStdlibApi::class)
            val components = buildMap<String, Long> {
                put("Init", initMeasurement?.milliseconds ?: 0)
                put("Analysis", analysisMeasurement?.milliseconds ?: 0)

                irMeasurements.firstOrNull { it.kind == IRMeasurement.Kind.TRANSLATION }?.milliseconds?.let { put("Translation", it) }
                irMeasurements.firstOrNull { it.kind == IRMeasurement.Kind.LOWERING }?.milliseconds?.let { put("Lowering", it) }

                val generationTime =
                    irMeasurements.firstOrNull { it.kind == IRMeasurement.Kind.GENERATION }?.milliseconds ?:
                    measurements.filterIsInstance<CodeGenerationMeasurement>().firstOrNull()?.milliseconds

                if (generationTime != null) {
                    put("Generation", generationTime)
                }
            }

            return CumulativeTime(
                gcInfo,
                components,
                files ?: 0,
                lines ?: 0
            )
        }
    }

    protected class TestMessageCollector : MessageCollector {

        data class Message(val severity: CompilerMessageSeverity, val message: String, val location: CompilerMessageSourceLocation?)

        val messages = arrayListOf<Message>()

        override fun clear() {
            messages.clear()
        }

        override fun report(severity: CompilerMessageSeverity, message: String, location: CompilerMessageSourceLocation?) {
            messages.add(Message(severity, message, location))
            if (severity in CompilerMessageSeverity.VERBOSE) return
            println(MessageRenderer.GRADLE_STYLE.render(severity, message, location))
        }

        override fun hasErrors(): Boolean = messages.any {
            it.severity == CompilerMessageSeverity.EXCEPTION || it.severity == CompilerMessageSeverity.ERROR
        }
    }


}