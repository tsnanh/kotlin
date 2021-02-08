object A {
    constructor()
    init {}
}

enum class B {
    X() {
        constructor()
    }
}

class C {
    companion object {
        constructor()
    }
}

val anonObject = object {
    constructor()
}

// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 38 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 7 LINE TEXT: object A {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 33 LINE TEXT: init {}
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 55 LINE TEXT: enum class B {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 106 LINE TEXT: class C {
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 124 LINE TEXT: companion object {
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 168 LINE TEXT: val anonObject = object {