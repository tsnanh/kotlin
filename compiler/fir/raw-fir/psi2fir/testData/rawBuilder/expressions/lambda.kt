// WITH_RUNTIME
data class Tuple(val x: Int, val y: Int)

inline fun use(f: (Tuple) -> Int) = f(Tuple(1, 2))

fun foo(): Int {
    val l1 = { t: Tuple ->
        val x = t.x
        val y = t.y
        x + y
    }
    use { (x, y) -> x + y }

    return use {
        if (it.x == 0) return@foo 0
        return@use it.y
    }
}

fun bar(): Int {
    return use lambda@{
        if (it.x == 0) return@bar 0
        return@lambda it.y
    }
}

fun test(list: List<Int>) {
    val map = mutableMapOf<Int, String>()
    list.forEach { map.getOrPut(it, { mutableListOf() }) += "" }
}

val simple = { }

val another = { 42 }

// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 125 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 135 LINE TEXT: val l1 = { t: Tuple ->
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 218 LINE TEXT: use { (x, y) -> x + y }
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 247 LINE TEXT: return use {
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 166 LINE TEXT: val x = t.x
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 186 LINE TEXT: val y = t.y
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 202 LINE TEXT: x + y
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 234 LINE TEXT: x + y
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 268 LINE TEXT: if (it.x == 0) return@foo 0
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 304 LINE TEXT: return@use it.y
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 344 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 350 LINE TEXT: return use lambda@{
// FIR_FRAGMENT_EXPECTED LINE: 22 TEXT OFFSET: 378 LINE TEXT: if (it.x == 0) return@bar 0
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 414 LINE TEXT: return@lambda it.y
// FIR_FRAGMENT_EXPECTED LINE: 27 TEXT OFFSET: 468 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 28 TEXT OFFSET: 478 LINE TEXT: val map = mutableMapOf<Int, String>()
// FIR_FRAGMENT_EXPECTED LINE: 29 TEXT OFFSET: 516 LINE TEXT: list.forEach { map.getOrPut(it, { mutableListOf() }) += "" }
// FIR_FRAGMENT_EXPECTED LINE: 29 TEXT OFFSET: 531 LINE TEXT: map.getOrPut(it, { mutableListOf() }) += ""
// FIR_FRAGMENT_EXPECTED LINE: 34 TEXT OFFSET: 614 LINE TEXT: 42
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 27 LINE TEXT: // WITH_RUNTIME
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 37 LINE TEXT: val x: Int
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 49 LINE TEXT: val y: Int
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 69 LINE TEXT: inline fun use(f: (Tuple) -> Int) = f(Tuple(1, 2))
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 73 LINE TEXT: f: (Tuple) -> Int
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 77 LINE TEXT: Tuple
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 114 LINE TEXT: fun foo(): Int {
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 333 LINE TEXT: fun bar(): Int {
// FIR_FRAGMENT_EXPECTED LINE: 27 TEXT OFFSET: 446 LINE TEXT: fun test(list: List<Int>) {
// FIR_FRAGMENT_EXPECTED LINE: 27 TEXT OFFSET: 451 LINE TEXT: list: List<Int>
// FIR_FRAGMENT_EXPECTED LINE: 32 TEXT OFFSET: 584 LINE TEXT: val simple = { }
// FIR_FRAGMENT_EXPECTED LINE: 34 TEXT OFFSET: 602 LINE TEXT: val another = { 42 }