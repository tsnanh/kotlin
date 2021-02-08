abstract class A {
    abstract class Nested
}

typealias TA = A

class B : TA() {
    class NestedInB : Nested()
}

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 15 LINE TEXT: abstract class A {
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 38 LINE TEXT: abstract class Nested
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 58 LINE TEXT: typealias TA = A
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 72 LINE TEXT: class B : TA() {
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 93 LINE TEXT: class NestedInB : Nested()