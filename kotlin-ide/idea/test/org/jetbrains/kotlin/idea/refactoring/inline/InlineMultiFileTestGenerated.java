/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.refactoring.inline;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.test.TestRoot;
import org.junit.runner.RunWith;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/refactoring/inlineMultiFile")
public class InlineMultiFileTestGenerated extends AbstractInlineMultiFileTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("complexJavaToKotlin2/complexJavaToKotlin2.test")
    public void testComplexJavaToKotlin2_ComplexJavaToKotlin2() throws Exception {
        runTest("testData/refactoring/inlineMultiFile/complexJavaToKotlin2/complexJavaToKotlin2.test");
    }

    @TestMetadata("complexJavaToKotlin/complexJavaToKotlin.test")
    public void testComplexJavaToKotlin_ComplexJavaToKotlin() throws Exception {
        runTest("testData/refactoring/inlineMultiFile/complexJavaToKotlin/complexJavaToKotlin.test");
    }

    @TestMetadata("delegateToCallChain/delegateToCallChain.test")
    public void testDelegateToCallChain_DelegateToCallChain() throws Exception {
        runTest("testData/refactoring/inlineMultiFile/delegateToCallChain/delegateToCallChain.test");
    }
}
