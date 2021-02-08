val p = 0
fun foo() = 1

class Wrapper(val v: IntArray)

fun test(a: IntArray, w: Wrapper) = a[0] + a[p] + a[foo()] + w.v[0]

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: val p = 0
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 14 LINE TEXT: fun foo() = 1
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 31 LINE TEXT: class Wrapper(val v: IntArray)
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 43 LINE TEXT: val v: IntArray
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 61 LINE TEXT: fun test(a: IntArray, w: Wrapper) = a[0] + a[p] + a[foo()] + w.v[0]
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 66 LINE TEXT: a: IntArray
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 79 LINE TEXT: w: Wrapper