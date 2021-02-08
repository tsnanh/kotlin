fun foo(first: String = "", second: Boolean = true, third: Double = 3.1415) {}

fun test() {
    foo()
    foo("Alpha", false, 2.71)
    foo(first = "Hello", second = true)
    foo(third = -1.0, first = "123")
    foo(= "")
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 76 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 91 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 97 LINE TEXT: foo()
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 107 LINE TEXT: foo("Alpha", false, 2.71)
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 137 LINE TEXT: foo(first = "Hello", second = true)
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 177 LINE TEXT: foo(third = -1.0, first = "123")
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 214 LINE TEXT: foo(=
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun foo(first: String = "", second: Boolean = true, third: Double = 3.1415) {}
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 8 LINE TEXT: first: String = ""
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 28 LINE TEXT: second: Boolean = true
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 52 LINE TEXT: third: Double = 3.1415
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 84 LINE TEXT: fun test() {