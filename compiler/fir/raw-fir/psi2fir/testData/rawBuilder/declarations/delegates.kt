import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val x: Int by lazy { 1 + 2 }

val delegate = object: ReadWriteProperty<Any?, Int> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int = 1
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {}
}

val value by delegate

var variable by delegate

// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 97 LINE TEXT: 1 + 2
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 311 LINE TEXT: {}
// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 80 LINE TEXT: val x: Int by lazy { 1 + 2 }
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 110 LINE TEXT: val delegate = object: ReadWriteProperty<Any?, Int> {
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 177 LINE TEXT: override fun getValue(thisRef: Any?, property: KProperty<*>): Int = 1
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 186 LINE TEXT: thisRef: Any?
// FIR_FRAGMENT_EXPECTED LINE: 7 TEXT OFFSET: 201 LINE TEXT: property: KProperty<*>
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 251 LINE TEXT: override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {}
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 260 LINE TEXT: thisRef: Any?
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 275 LINE TEXT: property: KProperty<*>
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 299 LINE TEXT: value: Int
// FIR_FRAGMENT_EXPECTED LINE: 11 TEXT OFFSET: 321 LINE TEXT: val value by delegate
// FIR_FRAGMENT_EXPECTED LINE: 13 TEXT OFFSET: 344 LINE TEXT: var variable by delegate