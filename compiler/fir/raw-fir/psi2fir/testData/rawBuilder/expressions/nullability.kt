fun orFourtyTwo(arg: Int?) = arg ?: 42

fun bang(arg: Int?) = arg!!

// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 4 LINE TEXT: fun orFourtyTwo(arg: Int?) = arg ?: 42
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 16 LINE TEXT: arg: Int?
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 44 LINE TEXT: fun bang(arg: Int?) = arg!!
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 49 LINE TEXT: arg: Int?