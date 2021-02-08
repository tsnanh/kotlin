fun foo(x: Int, y: Int, c: Collection<Int>) =
    x in c && y !in c

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun foo(x: Int, y: Int, c: Collection<Int>) =
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 8 LINE TEXT: x: Int
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 16 LINE TEXT: y: Int
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 24 LINE TEXT: c: Collection<Int>