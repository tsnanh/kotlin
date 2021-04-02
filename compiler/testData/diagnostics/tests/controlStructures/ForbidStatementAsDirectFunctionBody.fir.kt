
fun foo1() = <!EXPRESSION_REQUIRED!>while (b()) {}<!>

fun foo2() = <!ITERATOR_MISSING!>for (i in 10) {}<!>

fun foo3() = when (b()) {
    true -> 1
    else -> 0
}

fun b(): Boolean = true