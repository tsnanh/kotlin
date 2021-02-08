expect class MyClass

expect fun foo(): String

expect val x: Int

actual class MyClass

actual fun foo() = "Hello"

actual val x = 42

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 13 LINE TEXT: expect class MyClass
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 33 LINE TEXT: expect fun foo(): String
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 59 LINE TEXT: expect val x: Int
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 80 LINE TEXT: actual class MyClass
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 100 LINE TEXT: actual fun foo() = "Hello"
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 128 LINE TEXT: actual val x = 42