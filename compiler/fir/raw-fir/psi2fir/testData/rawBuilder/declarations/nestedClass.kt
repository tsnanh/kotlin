abstract class Base(val s: String)

class Outer {
    class Derived(s: String) : Base(s)

    object Obj : Base("")
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 15 LINE TEXT: abstract class Base(val s: String)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 24 LINE TEXT: val s: String
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 42 LINE TEXT: class Outer {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 60 LINE TEXT: class Derived(s: String) : Base(s)
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 101 LINE TEXT: object Obj : Base("")