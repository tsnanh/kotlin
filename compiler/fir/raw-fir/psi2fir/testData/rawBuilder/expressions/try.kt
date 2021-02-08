// WITH_RUNTIME
fun some() {
    try {
        throw KotlinNullPointerException()
    } catch (e: RuntimeException) {
        println("Runtime exception")
    } catch (e: Exception) {
        println("Some exception")
    } finally {
        println("finally")
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 27 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 33 LINE TEXT: try {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 37 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 47 LINE TEXT: throw KotlinNullPointerException()
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 116 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 126 LINE TEXT: println("Runtime exception")
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 182 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 192 LINE TEXT: println("Some exception")
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 232 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 242 LINE TEXT: println("finally")
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 20 LINE TEXT: // WITH_RUNTIME