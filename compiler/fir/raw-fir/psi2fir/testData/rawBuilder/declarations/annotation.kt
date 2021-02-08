@Target(AnnotationTarget.ANNOTATION_CLASS) annotation class base

@base annotation class derived

@base class correct(@base val x: Int) {
    @base constructor(): this(0)
}

@base enum class My {
    @base FIRST,
    @base SECOND
}

@base fun foo(@base y: @base Int): Int {
    @base fun bar(@base z: @base Int) = z + 1
    @base val local = bar(y)
    return local
}

@base val z = 0

// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 272 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 288 LINE TEXT: @base fun bar(@base z: @base Int) = z + 1
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 334 LINE TEXT: @base val local = bar(y)
// FIR_FRAGMENT_EXPECTED LINE: 17 TEXT OFFSET: 353 LINE TEXT: return local
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 60 LINE TEXT: @Target(AnnotationTarget.ANNOTATION_CLASS) annotation class base
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 89 LINE TEXT: @base annotation class derived
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 110 LINE TEXT: @base class correct(@base val x: Int) {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 128 LINE TEXT: @base val x: Int
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 191 LINE TEXT: @base enum class My {
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 243 LINE TEXT: @base fun foo(@base y: @base Int): Int {
// FIR_FRAGMENT_EXPECTED LINE: 14 TEXT OFFSET: 253 LINE TEXT: @base y: @base Int
// FIR_FRAGMENT_EXPECTED LINE: 15 TEXT OFFSET: 298 LINE TEXT: @base z: @base Int
// FIR_FRAGMENT_EXPECTED LINE: 20 TEXT OFFSET: 379 LINE TEXT: @base val z = 0