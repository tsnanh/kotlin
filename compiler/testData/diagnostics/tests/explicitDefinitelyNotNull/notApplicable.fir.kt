// !LANGUAGE: +DefinitelyNotNullTypeParameters

fun <T : Any> foo(x: T!!, y: List<String!!>!!) {}

fun <F> bar1(x: F?<!SYNTAX!><!SYNTAX!><!>!!<!><!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
fun <F> bar2(x: F!!?) {}
fun <F> bar3(x: (F?)<!SYNTAX!><!SYNTAX!><!>!!<!><!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
fun <F> bar4(x: (F!!)?) {}
