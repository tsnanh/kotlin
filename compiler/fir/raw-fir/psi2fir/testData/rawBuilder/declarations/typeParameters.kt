interface List<out T : Any> {
    operator fun get(index: Int): T

    infix fun concat(other: List<T>): List<T>
}

typealias StringList = List<out String>
typealias AnyList = List<*>

abstract class AbstractList<out T : Any> : List<T>

class SomeList : AbstractList<Int>() {
    override fun get(index: Int): Int = 42

    override fun concat(other: List<Int>): List<Int> = this
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: interface List<out T : Any> {
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 19 LINE TEXT: out T : Any
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 47 LINE TEXT: operator fun get(index: Int): T
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 51 LINE TEXT: index: Int
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 81 LINE TEXT: infix fun concat(other: List<T>): List<T>
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 88 LINE TEXT: other: List<T>
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 126 LINE TEXT: typealias StringList = List<out String>
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 166 LINE TEXT: typealias AnyList = List<*>
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 200 LINE TEXT: abstract class AbstractList<out T : Any> : List<T>
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 217 LINE TEXT: out T : Any
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 243 LINE TEXT: class SomeList : AbstractList<Int>() {
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 293 LINE TEXT: override fun get(index: Int): Int = 42
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 297 LINE TEXT: index: Int
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 337 LINE TEXT: override fun concat(other: List<Int>): List<Int> = this
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 344 LINE TEXT: other: List<Int>