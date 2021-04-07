// LANGUAGE: +SimplifiedIntegerLiteralOperatorResolution
// WITH_STDLIB
// ISSUE: KT-38895

fun takeByte(b: Byte) {}
fun takeInt(b: Int) {}
fun takeLong(b: Long) {}

fun testByteBinaryOperators() {
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 + 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 - 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 * 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 / 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 % 1)

    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.plus(1))
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.minus(1))
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.times(1))
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.div(1))
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.rem(1))
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 shl 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 shr 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 ushr 1)

    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 and 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 or 1)
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2 xor 1)
}

fun testByteUnaryOperators() {
    // No mismatch
    takeByte(+1)
    takeByte(-1)

    // Mismatch
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.unaryPlus())
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.unaryMinus())
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(2.inv())
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(1.inc())
    <!INAPPLICABLE_CANDIDATE!>takeByte<!>(1.dec())
}

fun testLongBinaryOperators() {
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 + 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 - 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 * 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 / 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 % 1)

    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.plus(1))
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.minus(1))
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.times(1))
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.div(1))
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.rem(1))
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 shl 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 shr 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 ushr 1)

    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 and 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 or 1)
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2 xor 1)

    // positive
    takeLong(2 * 100000000000)
}

fun testLongUnaryOperators() {
    // No mismatch
    takeLong(+1)
    takeLong(-1)

    // Mismatch
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.unaryPlus())
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.unaryMinus())
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(2.inv())
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(1.inc())
    <!INAPPLICABLE_CANDIDATE!>takeLong<!>(1.dec())
}

fun testIntBinaryOperators() {
    takeInt(2 + 1)
    takeInt(2 - 1)
    takeInt(2 * 1)
    takeInt(2 / 1)
    takeInt(2 % 1)

    takeInt(2.plus(1))
    takeInt(2.minus(1))
    takeInt(2.times(1))
    takeInt(2.div(1))
    takeInt(2.rem(1))
    takeInt(2 shl 1)
    takeInt(2 shr 1)
    takeInt(2 ushr 1)

    takeInt(2 and 1)
    takeInt(2 or 1)
    takeInt(2 xor 1)
}

fun testIntUnaryOperators() {
    takeInt(+1)
    takeInt(-1)

    takeInt(2.unaryPlus())
    takeInt(2.unaryMinus())
    takeInt(2.inv())
    takeInt(1.inc())
    takeInt(1.dec())
}

fun testNoOperators() {
    takeByte(1)
    takeInt(1)
    takeLong(1)
}
