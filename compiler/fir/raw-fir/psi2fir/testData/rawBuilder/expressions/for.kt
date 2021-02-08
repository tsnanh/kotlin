fun foo() {
    for (i in 1..10) {
        println(i)
    }
}

fun fooLabeled() {
    println("!!!")
    label@ for (i in 1..10) {
        if (i == 5) continue@label
        println(i)
    }
    println("!!!")
}

fun bar(list: List<String>) {
    for (element in list.subList(0, 10)) {
        println(element)
    }
    for (element in list.subList(10, 20)) println(element)
}

data class Some(val x: Int, val y: Int)

fun baz(set: Set<Some>) {
    for ((x, y) in set) {
        println("x = $x y = $y")
    }
}

fun withParameter(list: List<Some>) {
    for (s: Some in list) {
        println(s)
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 16 LINE TEXT: for (i in 1..10) {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 43 LINE TEXT: println(i)
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 80 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 86 LINE TEXT: println("!!!")
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 195 LINE TEXT: println("!!!")
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 139 LINE TEXT: if (i == 5) continue@label
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 174 LINE TEXT: println(i)
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 241 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 17 TEXT OFFSET: 247 LINE TEXT: for (element in list.subList(0, 10)) {
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 321 LINE TEXT: for (element in list.subList(10, 20)) println(element)
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 294 LINE TEXT: println(element)
// FIR_FRAGMENT_EXPECTED LINE: 25 TEXT OFFSET: 444 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 26 TEXT OFFSET: 450 LINE TEXT: for ((x, y) in set) {
// FIR_FRAGMENT_EXPECTED LINE: 27 TEXT OFFSET: 480 LINE TEXT: println("x = $x y = $y")
// FIR_FRAGMENT_EXPECTED LINE: 31 TEXT OFFSET: 550 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 32 TEXT OFFSET: 556 LINE TEXT: for (s: Some in list) {
// FIR_FRAGMENT_EXPECTED LINE: 33 TEXT OFFSET: 588 LINE TEXT: println(s)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun foo() {
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 67 LINE TEXT: fun fooLabeled() {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 217 LINE TEXT: fun bar(list: List<String>) {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 221 LINE TEXT: list: List<String>
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 390 LINE TEXT: data class Some(val x: Int, val y: Int)
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 399 LINE TEXT: val x: Int
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 411 LINE TEXT: val y: Int
// FIR_FRAGMENT_EXPECTED LINE: 25 TEXT OFFSET: 424 LINE TEXT: fun baz(set: Set<Some>) {
// FIR_FRAGMENT_EXPECTED LINE: 25 TEXT OFFSET: 428 LINE TEXT: set: Set<Some>
// FIR_FRAGMENT_EXPECTED LINE: 31 TEXT OFFSET: 518 LINE TEXT: fun withParameter(list: List<Some>) {
// FIR_FRAGMENT_EXPECTED LINE: 31 TEXT OFFSET: 532 LINE TEXT: list: List<Some>
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 112 LINE TEXT: for (i in 1..10) {