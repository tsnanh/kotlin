fun withLocals(p: Int): Int {
    class Local(val pp: Int) {
        fun diff() = pp - p
    }

    val x = Local(42).diff()

    fun sum(y: Int, z: Int, f: (Int, Int) -> Int): Int {
        return x + f(y + z)
    }

    val code = (object : Any() {
        fun foo() = hashCode()
    }).foo()

    return sum(code, Local(1).diff(), fun(x: Int, y: Int) = x + y)
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 28 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 40 LINE TEXT: class Local(val pp: Int) {
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 104 LINE TEXT: val x = Local(42).diff()
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 134 LINE TEXT: fun sum(y: Int, z: Int, f: (Int, Int) -> Int): Int {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 226 LINE TEXT: val code = (object : Any() {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 300 LINE TEXT: return sum(code, Local(1).diff(), fun(x: Int, y: Int) = x + y)
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 181 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 191 LINE TEXT: return x + f(y + z)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun withLocals(p: Int): Int {
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 15 LINE TEXT: p: Int
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 50 LINE TEXT: val pp: Int
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 73 LINE TEXT: fun diff() = pp - p
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 138 LINE TEXT: y: Int
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 146 LINE TEXT: z: Int
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 154 LINE TEXT: f: (Int, Int) -> Int
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 158 LINE TEXT: Int
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 163 LINE TEXT: Int
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 263 LINE TEXT: fun foo() = hashCode()
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 334 LINE TEXT: fun(x: Int, y: Int) = x + y
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 338 LINE TEXT: x: Int
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 346 LINE TEXT: y: Int