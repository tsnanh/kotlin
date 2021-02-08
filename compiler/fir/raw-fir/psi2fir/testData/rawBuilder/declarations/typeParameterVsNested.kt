package test

interface Some

abstract class My<T : Some> {
    inner class T

    abstract val x: T

    abstract fun foo(arg: T)

    abstract val y: My.T

    abstract val z: test.My.T

    class Some : T()
}

// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 24 LINE TEXT: interface Some
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 45 LINE TEXT: abstract class My<T : Some> {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 48 LINE TEXT: T : Some
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 76 LINE TEXT: inner class T
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 119 LINE TEXT: abstract fun foo(arg: T)
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 123 LINE TEXT: arg: T
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 199 LINE TEXT: class Some : T()