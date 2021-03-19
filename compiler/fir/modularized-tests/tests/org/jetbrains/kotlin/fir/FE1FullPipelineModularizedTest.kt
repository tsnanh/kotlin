/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir

import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.toBooleanLenient
import org.jetbrains.kotlin.fir.scopes.ProcessorAction


private val USE_BE_IR = System.getProperty("fir.bench.fe1.useIR", "false").toBooleanLenient()!!

class FE1FullPipelineModularizedTest : AbstractFullPipelineModularizedTest() {
    override fun configureArguments(args: K2JVMCompilerArguments, moduleData: ModuleData) {
        args.useIR = USE_BE_IR
        args.useFir = false
        args.jvmDefault = "compatibility"
        args.optIn = arrayOf("kotlin.RequiresOptIn")
    }

    override fun handleResult(result: ExitCode, moduleData: ModuleData, collector: TestMessageCollector): ProcessorAction {
        return ProcessorAction.NEXT
    }

    override fun afterPass(pass: Int) {
        createReport(finalReport = pass == PASSES - 1)
    }

    fun testTotalKotlin() {
        for (i in 0 until PASSES) {
            println("Pass $i")
            runTestOnce(i)
        }
    }
}