// WITH_RUNTIME
infix fun distance(x: Int, y: Int) = x + y

fun test(): Int = 3 distance 4

fun testRegular(): Int = distance(3, 4)

class My(var x: Int) {
    operator fun invoke() = x

    fun foo() {}

    fun copy() = My(x)
}

fun testInvoke(): Int = My(13)()

fun testQualified(first: My, second: My?) {
    println(first.x)
    println(second?.x)
    first.foo()
    second?.foo()
    first.copy().foo()
    first.x = 42
}

// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 201 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 307 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 19 TEXT OFFSET: 313 LINE TEXT: println(first.x)
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 334 LINE TEXT: println(second?.x)
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 357 LINE TEXT: first.foo()
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 391 LINE TEXT: first.copy().foo()
// FIR_FRAGMENT_EXPECTED LINE: 24 TEXT OFFSET: 414 LINE TEXT: first.x = 42
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 26 LINE TEXT: // WITH_RUNTIME
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 35 LINE TEXT: x: Int
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 43 LINE TEXT: y: Int
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 64 LINE TEXT: fun test(): Int = 3 distance 4
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 96 LINE TEXT: fun testRegular(): Int = distance(3, 4)
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 139 LINE TEXT: class My(var x: Int) {
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 146 LINE TEXT: var x: Int
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 173 LINE TEXT: operator fun invoke() = x
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 195 LINE TEXT: fun foo() {}
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 213 LINE TEXT: fun copy() = My(x)
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 235 LINE TEXT: fun testInvoke(): Int = My(13)()
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 269 LINE TEXT: fun testQualified(first: My, second: My?) {
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 283 LINE TEXT: first: My
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 294 LINE TEXT: second: My?