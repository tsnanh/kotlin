fun <T> nullableValue(): T? = null

fun test() {
    val n = nullableValue<Int>()
    val x = nullableValue<Double>()
    val s = nullableValue<String>()
}

// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 47 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 57 LINE TEXT: val n = nullableValue<Int>()
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 90 LINE TEXT: val x = nullableValue<Double>()
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 126 LINE TEXT: val s = nullableValue<String>()
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 8 LINE TEXT: fun <T> nullableValue(): T? = null
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 5 LINE TEXT: T
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 40 LINE TEXT: fun test() {