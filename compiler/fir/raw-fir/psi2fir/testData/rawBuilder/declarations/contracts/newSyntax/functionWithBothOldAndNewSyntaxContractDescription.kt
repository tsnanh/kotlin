fun test1(s: String?) contract [returnsNotNull()] {
    contract {
        returns() implies (s != null)
    }
    test1()
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 50 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 56 LINE TEXT: contract {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 115 LINE TEXT: test1()
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 75 LINE TEXT: returns() implies (s != null)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun test1(s: String?) contract [returnsNotNull()] {
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: s: String?