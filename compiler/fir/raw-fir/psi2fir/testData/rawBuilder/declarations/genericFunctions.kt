interface Any

inline fun <reified T : Any> Any.safeAs(): T? = this as? T

abstract class Summator {
    abstract fun <T> plus(first: T, second: T): T
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: interface Any
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 48 LINE TEXT: inline fun <reified T : Any> Any.safeAs(): T? = this as? T
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 35 LINE TEXT: reified T : Any
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 90 LINE TEXT: abstract class Summator {
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 122 LINE TEXT: abstract fun <T> plus(first: T, second: T): T
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 119 LINE TEXT: T
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 127 LINE TEXT: first: T
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 137 LINE TEXT: second: T