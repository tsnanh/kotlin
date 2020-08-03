/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/fir/analysis-tests/testData/extendedCheckers")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ExtendedFirDiagnosticsTestGenerated extends AbstractExtendedFirDiagnosticsTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInExtendedCheckers() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/analysis-tests/testData/extendedCheckers"), Pattern.compile("^([^.]+)\\.kt$"), null, true);
    }

    @TestMetadata("ArrayEqualityCanBeReplacedWithEquals.kt")
    public void testArrayEqualityCanBeReplacedWithEquals() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/ArrayEqualityCanBeReplacedWithEquals.kt");
    }

    @TestMetadata("RedundantExplicitTypeChecker.kt")
    public void testRedundantExplicitTypeChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantExplicitTypeChecker.kt");
    }

    @TestMetadata("RedundantModalityModifierChecker.kt")
    public void testRedundantModalityModifierChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantModalityModifierChecker.kt");
    }

    @TestMetadata("RedundantReturnUnitTypeChecker.kt")
    public void testRedundantReturnUnitTypeChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantReturnUnitTypeChecker.kt");
    }

    @TestMetadata("RedundantSingleExpressionStringTemplateChecker.kt")
    public void testRedundantSingleExpressionStringTemplateChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantSingleExpressionStringTemplateChecker.kt");
    }

    @TestMetadata("RedundantVisibilityModifierChecker.kt")
    public void testRedundantVisibilityModifierChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantVisibilityModifierChecker.kt");
    }

    @TestMetadata("VariableAssignmentChecker.kt")
    public void testVariableAssignmentChecker() throws Exception {
        runTest("compiler/fir/analysis-tests/testData/extendedCheckers/VariableAssignmentChecker.kt");
    }

    @TestMetadata("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class CanBeReplacedWithOperatorAssignment extends AbstractExtendedFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInCanBeReplacedWithOperatorAssignment() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment"), Pattern.compile("^([^.]+)\\.kt$"), null, true);
        }

        @TestMetadata("BasicTest.kt")
        public void testBasicTest() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/BasicTest.kt");
        }

        @TestMetadata("ComplexExpression.kt")
        public void testComplexExpression() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/ComplexExpression.kt");
        }

        @TestMetadata("flexibleTypeBug.kt")
        public void testFlexibleTypeBug() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/flexibleTypeBug.kt");
        }

        @TestMetadata("illegalMultipleOperators.kt")
        public void testIllegalMultipleOperators() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/illegalMultipleOperators.kt");
        }

        @TestMetadata("illegalMultipleOperatorsMiddle.kt")
        public void testIllegalMultipleOperatorsMiddle() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/illegalMultipleOperatorsMiddle.kt");
        }

        @TestMetadata("invalidSubtraction.kt")
        public void testInvalidSubtraction() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/invalidSubtraction.kt");
        }

        @TestMetadata("list.kt")
        public void testList() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/list.kt");
        }

        @TestMetadata("logicOperators.kt")
        public void testLogicOperators() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/logicOperators.kt");
        }

        @TestMetadata("multipleOperators.kt")
        public void testMultipleOperators() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/multipleOperators.kt");
        }

        @TestMetadata("multipleOperatorsRightSideRepeat.kt")
        public void testMultipleOperatorsRightSideRepeat() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/multipleOperatorsRightSideRepeat.kt");
        }

        @TestMetadata("mutableList.kt")
        public void testMutableList() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/mutableList.kt");
        }

        @TestMetadata("nonCommutativeRepeat.kt")
        public void testNonCommutativeRepeat() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/nonCommutativeRepeat.kt");
        }

        @TestMetadata("nonRepeatingAssignment.kt")
        public void testNonRepeatingAssignment() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/nonRepeatingAssignment.kt");
        }

        @TestMetadata("OperatorAssignment.kt")
        public void testOperatorAssignment() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/OperatorAssignment.kt");
        }

        @TestMetadata("plusAssignConflict.kt")
        public void testPlusAssignConflict() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/plusAssignConflict.kt");
        }

        @TestMetadata("rightSideRepeat.kt")
        public void testRightSideRepeat() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/rightSideRepeat.kt");
        }

        @TestMetadata("simpleAssign.kt")
        public void testSimpleAssign() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/simpleAssign.kt");
        }

        @TestMetadata("validAddition.kt")
        public void testValidAddition() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/validAddition.kt");
        }

        @TestMetadata("validSubtraction.kt")
        public void testValidSubtraction() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/canBeReplacedWithOperatorAssignment/validSubtraction.kt");
        }
    }

    @TestMetadata("compiler/fir/analysis-tests/testData/extendedCheckers/emptyRangeChecker")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class EmptyRangeChecker extends AbstractExtendedFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInEmptyRangeChecker() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/analysis-tests/testData/extendedCheckers/emptyRangeChecker"), Pattern.compile("^([^.]+)\\.kt$"), null, true);
        }

        @TestMetadata("NoWarning.kt")
        public void testNoWarning() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/emptyRangeChecker/NoWarning.kt");
        }

        @TestMetadata("Warning.kt")
        public void testWarning() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/emptyRangeChecker/Warning.kt");
        }
    }

    @TestMetadata("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class RedundantCallOfConversionMethod extends AbstractExtendedFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInRedundantCallOfConversionMethod() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod"), Pattern.compile("^([^.]+)\\.kt$"), null, true);
        }

        @TestMetadata("booleanToInt.kt")
        public void testBooleanToInt() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/booleanToInt.kt");
        }

        @TestMetadata("byte.kt")
        public void testByte() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/byte.kt");
        }

        @TestMetadata("char.kt")
        public void testChar() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/char.kt");
        }

        @TestMetadata("double.kt")
        public void testDouble() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/double.kt");
        }

        @TestMetadata("float.kt")
        public void testFloat() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/float.kt");
        }

        @TestMetadata("int.kt")
        public void testInt() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/int.kt");
        }

        @TestMetadata("long.kt")
        public void testLong() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/long.kt");
        }

        @TestMetadata("nullable.kt")
        public void testNullable() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/nullable.kt");
        }

        @TestMetadata("nullable2.kt")
        public void testNullable2() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/nullable2.kt");
        }

        @TestMetadata("safeString.kt")
        public void testSafeString() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/safeString.kt");
        }

        @TestMetadata("safeString2.kt")
        public void testSafeString2() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/safeString2.kt");
        }

        @TestMetadata("short.kt")
        public void testShort() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/short.kt");
        }

        @TestMetadata("string.kt")
        public void testString() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/string.kt");
        }

        @TestMetadata("StringTemplate.kt")
        public void testStringTemplate() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/StringTemplate.kt");
        }

        @TestMetadata("toOtherType.kt")
        public void testToOtherType() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/toOtherType.kt");
        }

        @TestMetadata("uByte.kt")
        public void testUByte() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/uByte.kt");
        }

        @TestMetadata("uInt.kt")
        public void testUInt() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/uInt.kt");
        }

        @TestMetadata("uLong.kt")
        public void testULong() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/uLong.kt");
        }

        @TestMetadata("uShort.kt")
        public void testUShort() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/uShort.kt");
        }

        @TestMetadata("variable.kt")
        public void testVariable() throws Exception {
            runTest("compiler/fir/analysis-tests/testData/extendedCheckers/RedundantCallOfConversionMethod/variable.kt");
        }
    }
}
