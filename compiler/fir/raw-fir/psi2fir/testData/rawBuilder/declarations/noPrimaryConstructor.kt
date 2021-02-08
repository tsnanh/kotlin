class NoPrimary {
    val x: String

    constructor(x: String) {
        this.x = x
    }

    constructor(): this("")
}

// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 64 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 74 LINE TEXT: this.x = x
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 6 LINE TEXT: class NoPrimary {