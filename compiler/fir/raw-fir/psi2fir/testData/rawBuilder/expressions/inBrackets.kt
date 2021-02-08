fun test(e: Int.() -> String) {
    val s = 3.e()
    val ss = 3.(e)()
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 30 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 40 LINE TEXT: val s = 3.e()
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 58 LINE TEXT: val ss = 3.(e)()
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun test(e: Int.() -> String) {
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 9 LINE TEXT: e: Int.() -> String