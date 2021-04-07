// SKIP_TXT

// TESTCASE NUMBER: 1
fun case_1() {
    0L checkType { check<Long>() }
    10000000000000L checkType { check<Long>() }
    0X000Af10cDL checkType { check<Long>() }
    0x0_0L checkType { check<Long>() }
    0b100_000_111_111L checkType { check<Long>() }
    0b0L checkType { check<Long>() }
}
