import my.println

enum class Order {
    FIRST,
    SECOND,
    THIRD
}

enum class Planet(val m: Double, internal val r: Double) {
    MERCURY(1.0, 2.0) {
        override fun sayHello() {
            println("Hello!!!")
        }
    },
    VENERA(3.0, 4.0) {
        override fun sayHello() {
            println("Ola!!!")
        }
    },
    EARTH(5.0, 6.0) {
        override fun sayHello() {
            println("Privet!!!")
        }
    };

    val g: Double = G * m / (r * r)

    abstract fun sayHello()

    companion object {
        const val G = 6.67e-11
    }
}

enum class PseudoInsn(val signature: String = "()V") {
    FIX_STACK_BEFORE_JUMP,
    FAKE_ALWAYS_TRUE_IFEQ("()I"),
    FAKE_ALWAYS_FALSE_IFEQ("()I"),
    SAVE_STACK_BEFORE_TRY,
    RESTORE_STACK_IN_TRY_CATCH,
    STORE_NOT_NULL,
    AS_NOT_NULL("(Ljava/lang/Object;)Ljava/lang/Object;")
    ;

    fun emit() {}
}

// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 189 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 203 LINE TEXT: println("Hello!!!")
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 295 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 17 TEXT OFFSET: 309 LINE TEXT: println("Ola!!!")
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 398 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 22 TEXT OFFSET: 412 LINE TEXT: println("Privet!!!")
// FIR_FRAGMENT_EXPECTED LINE: 45 TEXT OFFSET: 890 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 30 LINE TEXT: enum class Order {
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 85 LINE TEXT: enum class Planet(val m: Double, internal val r: Double) {
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 96 LINE TEXT: val m: Double
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 120 LINE TEXT: internal val r: Double
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 178 LINE TEXT: override fun sayHello() {
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 284 LINE TEXT: override fun sayHello() {
// FIR_FRAGMENT_EXPECTED LINE: 21 TEXT OFFSET: 387 LINE TEXT: override fun sayHello() {
// FIR_FRAGMENT_EXPECTED LINE: 28 TEXT OFFSET: 505 LINE TEXT: abstract fun sayHello()
// FIR_FRAGMENT_EXPECTED LINE: 30 TEXT OFFSET: 531 LINE TEXT: companion object {
// FIR_FRAGMENT_EXPECTED LINE: 35 TEXT OFFSET: 591 LINE TEXT: enum class PseudoInsn(val signature: String = "()V") {
// FIR_FRAGMENT_EXPECTED LINE: 35 TEXT OFFSET: 606 LINE TEXT: val signature: String = "()V"
// FIR_FRAGMENT_EXPECTED LINE: 45 TEXT OFFSET: 883 LINE TEXT: fun emit() {}