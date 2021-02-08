// WITH_RUNTIME
fun foo(a: Int, b: Int) = if (a > b) a else b

fun bar(a: Double, b: Double): Double {
    if (a > b) {
        println(a)
        return a
    } else {
        println(b)
        return b
    }
}

fun baz(a: Long, b: Long): Long {
    when {
        a > b -> {
            println(a)
            return a
        }
        else -> return b
    }
}

fun grade(g: Int): String {
    return when (g) {
        6, 7 -> "Outstanding"
        5 -> "Excellent"
        4 -> "Good"
        3 -> "Mediocre"
        in 1..2 -> "Fail"
        is Number -> "Number"
        else -> "Unknown"
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 101 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 107 LINE TEXT: if (a > b) {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 118 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 128 LINE TEXT: println(a)
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 147 LINE TEXT: return a
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 167 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 177 LINE TEXT: println(b)
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 196 LINE TEXT: return b
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 246 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 252 LINE TEXT: when {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 276 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 17 TEXT OFFSET: 290 LINE TEXT: println(a)
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 313 LINE TEXT: return a
// FIR_FRAGMENT_EXPECTED LINE: 24 TEXT OFFSET: 392 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 25 TEXT OFFSET: 398 LINE TEXT: return when (g) {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 20 LINE TEXT: // WITH_RUNTIME
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 24 LINE TEXT: a: Int
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 32 LINE TEXT: b: Int
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 67 LINE TEXT: fun bar(a: Double, b: Double): Double {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 71 LINE TEXT: a: Double
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 82 LINE TEXT: b: Double
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 218 LINE TEXT: fun baz(a: Long, b: Long): Long {
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 222 LINE TEXT: a: Long
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 231 LINE TEXT: b: Long
// FIR_FRAGMENT_EXPECTED LINE: 24 TEXT OFFSET: 370 LINE TEXT: fun grade(g: Int): String {
// FIR_FRAGMENT_EXPECTED LINE: 24 TEXT OFFSET: 376 LINE TEXT: g: Int