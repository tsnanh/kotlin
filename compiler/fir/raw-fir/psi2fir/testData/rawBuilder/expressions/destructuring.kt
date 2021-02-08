data class Some(val first: Int, val second: Double, val third: String)

fun foo(some: Some) {
    var (x, y, z: String) = some

    x++
    y *= 2.0
    z = ""
}

fun bar(some: Some) {
    val (a, _, `_`) = some
}

// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 92 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 98 LINE TEXT: var (x, y, z: String) = some
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 140 LINE TEXT: y *= 2.0
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 153 LINE TEXT: z = ""
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 183 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 12 TEXT OFFSET: 189 LINE TEXT: val (a, _, `_`) = some
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 11 LINE TEXT: data class Some(val first: Int, val second: Double, val third: String)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 20 LINE TEXT: val first: Int
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 36 LINE TEXT: val second: Double
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 56 LINE TEXT: val third: String
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 76 LINE TEXT: fun foo(some: Some) {
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 80 LINE TEXT: some: Some
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 167 LINE TEXT: fun bar(some: Some) {
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 171 LINE TEXT: some: Some