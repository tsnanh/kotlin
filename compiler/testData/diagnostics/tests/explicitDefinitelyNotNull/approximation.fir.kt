// !LANGUAGE: +DefinitelyNotNullTypeParameters

fun <T> foo(x: T, y: T!!) = x!!

fun main() {
    foo<String>("", "").length
    <!INAPPLICABLE_CANDIDATE!>foo<!><String>("", null).length
    foo<String?>(null, "")<!UNSAFE_CALL!>.<!>length
    foo<String?>(null, null)<!UNSAFE_CALL!>.<!>length

    foo("", "").length
    foo("", null)<!UNSAFE_CALL!>.<!>length
    foo(null, "")<!UNSAFE_CALL!>.<!>length
}
