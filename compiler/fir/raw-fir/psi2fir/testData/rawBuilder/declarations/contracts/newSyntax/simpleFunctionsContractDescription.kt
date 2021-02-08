// new contracts syntax for simple functions
fun test1(s: MyClass?) contract [returns() implies (s != null), returns() implies (s is MySubClass)] {
    test_1()
}

fun test2() contract [returnsNotNull()] {
    test2()
}

// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 146 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 152 LINE TEXT: test_1()
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 204 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 210 LINE TEXT: test2()
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 49 LINE TEXT: // new contracts syntax for simple functions
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 55 LINE TEXT: s: MyClass?
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 168 LINE TEXT: fun test2() contract [returnsNotNull()] {