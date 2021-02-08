interface SomeInterface {
    fun foo(x: Int, y: String): String

    val bar: Boolean
}

class SomeClass : SomeInterface {
    private val baz = 42

    override fun foo(x: Int, y: String): String {
        return y + x + baz
    }

    override var bar: Boolean
        get() = true
        set(value) {}

    lateinit var fau: Double
}

inline class InlineClass

// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 198 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 208 LINE TEXT: return y + x + baz
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 304 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: interface SomeInterface {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 34 LINE TEXT: fun foo(x: Int, y: String): String
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 38 LINE TEXT: x: Int
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 46 LINE TEXT: y: String
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 96 LINE TEXT: class SomeClass : SomeInterface {
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 167 LINE TEXT: override fun foo(x: Int, y: String): String {
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 171 LINE TEXT: x: Int
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 179 LINE TEXT: y: String
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 353 LINE TEXT: inline class InlineClass