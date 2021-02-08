package p

abstract class My {
    abstract class NestedOne : My() {
        abstract class NestedTwo : NestedOne() {

        }
    }
}

class Your : My() {
    class NestedThree : NestedOne()
}

// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 26 LINE TEXT: abstract class My {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 50 LINE TEXT: abstract class NestedOne : My() {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 92 LINE TEXT: abstract class NestedTwo : NestedOne() {
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 144 LINE TEXT: class Your : My() {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 168 LINE TEXT: class NestedThree : NestedOne()