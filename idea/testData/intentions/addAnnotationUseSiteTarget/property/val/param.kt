// CHOOSE_USE_SITE_TARGET: param
// IS_APPLICABLE: false

annotation class A

class Property {
    @A<caret>
    val foo: String = ""
}