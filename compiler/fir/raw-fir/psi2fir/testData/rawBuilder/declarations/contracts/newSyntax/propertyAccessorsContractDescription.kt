// new contracts syntax for property accessors
class MyClass {
    var myInt: Int = 0
        get() contract [returnsNotNull()] = 1
    set(value) {
        field = value * 10
    }
}

class AnotherClass(multiplier: Int) {
    var anotherInt: Int = 0
        get() contract [returnsNotNull()] = 1
    set(value) contract [returns()] {
        field = value * multiplier
    }
}

class SomeClass(multiplier: Int?) {
    var someInt: Int = 0
        get() contract [returnsNotNull()] = 1
    set(value) contract [returns() implies (value != null)] {
        value ?: throw NullArgumentException()
        field = value
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 147 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 157 LINE TEXT: field = value * 10
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 333 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 343 LINE TEXT: field = value * multiplier
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 546 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 22 TEXT OFFSET: 556 LINE TEXT: value ?: throw NullArgumentException()
// FIR_FRAGMENT_EXPECTED LINE: 23 TEXT OFFSET: 603 LINE TEXT: field = value
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 53 LINE TEXT: // new contracts syntax for property accessors
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 191 LINE TEXT: class AnotherClass(multiplier: Int) {
// FIR_FRAGMENT_EXPECTED LINE: 18 TEXT OFFSET: 385 LINE TEXT: class SomeClass(multiplier: Int?) {