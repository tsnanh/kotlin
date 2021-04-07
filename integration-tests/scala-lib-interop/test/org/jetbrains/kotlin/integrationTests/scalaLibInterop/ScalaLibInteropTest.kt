/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.integrationTests.scalaLibInterop

import junit.framework.Assert.assertEquals
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.nio.file.Files
import kotlin.concurrent.thread

class ScalaLibInteropTest {

    @Test
    fun testKt38325() {
        withTempDir { outDir ->
            val classPath = System.getProperty("testsCompilationClasspath")
            val compiler = K2JVMCompiler()
            val compilerExitCode =
                compiler.exec(
                    System.err,
                    "-d", outDir.absolutePath, /*"-no-stdlib'", */"-cp", classPath, File(TEST_DATA_DIR, "kt38325.kt").path
                )
            Assert.assertEquals(ExitCode.OK, compilerExitCode)
            val args = listOf(
                File(File(System.getProperty("java.home"), "bin"), "java").absolutePath,
                "-cp", classPath + ":" + outDir.absolutePath,
                "Kt38325Kt"
            )
            var testOutput: String? = null
            var testExitCode: Int? = null
            val runnerProcess = ProcessBuilder(args).redirectErrorStream(true).start()
            val reader = thread {
                testOutput = runnerProcess.inputStream.reader().readText()
            }
            testExitCode = runnerProcess.waitFor()
            reader.join()

            assertEquals("Run test failed:\n$testExitCode\n$testOutput", 0, testExitCode)
            assertEquals("List(abc, def, ghi)", testOutput?.trim())
        }
    }
}

internal const val TEST_DATA_DIR = "testData"

internal fun <R> withTempDir(keyName: String = "tmp", body: (File) -> R) {
    val tempDir = Files.createTempDirectory(keyName).toFile()
    try {
        body(tempDir)
    } finally {
        tempDir.deleteRecursively()
    }
}
