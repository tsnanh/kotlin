interface Some

object O1 : Some

object O2 : Some

enum class SomeEnum(val x: Some) {
    FIRST(O1) {
        override fun check(y: Some): Boolean = true
    },
    SECOND(O2)  {
        override fun check(y: Some): Boolean = y == O2
    };

    abstract fun check(y: Some): Boolean
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 10 LINE TEXT: interface Some
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 23 LINE TEXT: object O1 : Some
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 41 LINE TEXT: object O2 : Some
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 63 LINE TEXT: enum class SomeEnum(val x: Some) {
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 76 LINE TEXT: val x: Some
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 124 LINE TEXT: override fun check(y: Some): Boolean = true
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 130 LINE TEXT: y: Some
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 201 LINE TEXT: override fun check(y: Some): Boolean = y == O2
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 207 LINE TEXT: y: Some
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 260 LINE TEXT: abstract fun check(y: Some): Boolean
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 266 LINE TEXT: y: Some