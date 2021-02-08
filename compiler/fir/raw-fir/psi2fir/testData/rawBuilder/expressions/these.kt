class Some {
    fun foo(): Int = 1

    fun bar(): Int {
        return this.foo()
    }

    val instance: Some
        get() = this@Some

    fun String.extension(): Int {
        return this@Some.bar() + this.length
    }
}

fun Some.extension() = this.bar()

fun test(some: Some): Int {
    return with(some) {
        this.foo() + this@with.extension()
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 56 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 66 LINE TEXT: return this.foo()
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 173 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 183 LINE TEXT: return this@Some.bar() + this.length
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 290 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 19 TEXT OFFSET: 296 LINE TEXT: return with(some) {
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 324 LINE TEXT: this.foo() + this@with.extension()
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 6 LINE TEXT: class Some {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 21 LINE TEXT: fun foo(): Int = 1
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 45 LINE TEXT: fun bar(): Int {
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 156 LINE TEXT: fun String.extension(): Int {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 238 LINE TEXT: fun Some.extension() = this.bar()
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 268 LINE TEXT: fun test(some: Some): Int {
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 273 LINE TEXT: some: Some