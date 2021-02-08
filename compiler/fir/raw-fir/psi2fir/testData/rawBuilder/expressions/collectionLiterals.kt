annotation class Ann1(val arr: IntArray)

annotation class Ann2(val arr: DoubleArray)

annotation class Ann3(val arr: Array<String>)

@Ann1([])
@Ann2([])
@Ann3([])
class Zero

@Ann1([1, 2])
class First

@Ann2([3.14])
class Second

@Ann3(["Alpha", "Omega"])
class Third

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 17 LINE TEXT: annotation class Ann1(val arr: IntArray)
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 26 LINE TEXT: val arr: IntArray
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 59 LINE TEXT: annotation class Ann2(val arr: DoubleArray)
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 68 LINE TEXT: val arr: DoubleArray
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 104 LINE TEXT: annotation class Ann3(val arr: Array<String>)
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 113 LINE TEXT: val arr: Array<String>
// FIR_FRAGMENT_EXPECTED LINE: 10 TEXT OFFSET: 170 LINE TEXT: @Ann1([])
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 196 LINE TEXT: @Ann1([1, 2])
// FIR_FRAGMENT_EXPECTED LINE: 16 TEXT OFFSET: 223 LINE TEXT: @Ann2([3.14])
// FIR_FRAGMENT_EXPECTED LINE: 19 TEXT OFFSET: 263 LINE TEXT: @Ann3(["Alpha", "Omega"])