// !LANGUAGE: +DefinitelyNotNullTypeParameters

fun <T> toDefNotNull(s: T): T!! = s!!

fun <K> removeQuestionMark(x: K?): K = x!!

fun Any.foo() {}

fun <F> main(x: F) {
    val y1 = toDefNotNull(x) // K instead of K!!
    val y2: F!! = toDefNotNull(x) // K instead of K!!
    val x1 = removeQuestionMark(x) // T or T!!
    val x2: F!! = removeQuestionMark(x) // T or T!!

    y1.<!INAPPLICABLE_CANDIDATE!>foo<!>()
    y2.<!INAPPLICABLE_CANDIDATE!>foo<!>()
    x1.foo()
    x2.<!INAPPLICABLE_CANDIDATE!>foo<!>()
}
