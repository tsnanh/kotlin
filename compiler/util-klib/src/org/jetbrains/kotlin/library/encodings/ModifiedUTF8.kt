/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("NOTHING_TO_INLINE")

package org.jetbrains.kotlin.library.encodings

import java.io.DataOutput

/**
 * Modified UTF-8 encoding.
 *
 * - Allows unpaired surrogates.
 * - Encodes null character (U+0000) using two non-null bytes: 11000000 10000000. So modified UTF-8 strings
 *   never contain null bytes and (with null byte appended) can be processed by traditional null-terminating
 *   string functions.
 * - Conforms to serialized String representation in JVM bytecode. See https://docs.oracle.com/javase/specs/jvms/se16/html/jvms-4.html#jvms-4.4.7
 *
 * See also [DataOutput.writeUTF].
 */
object ModifiedUTF8 {

    fun encode(string: String): ByteArray {
        val stringLength = string.length
        if (stringLength == 0) return EMPTY_BYTE_ARRAY

        val buffer = allocateByteArray(string, stringLength)
        var writtenBytes = 0

        var index = 0
        while (index < stringLength) {
            val char = string.readCharAsInt(index++)
            when {
                char != 0 && char < 0x80 -> {
                    // U+0001..U+007F -> 0xxxxxxx
                    // 7 meaningful bits -> 1 byte
                    buffer[writtenBytes++] = char
                }
                char < 0x0800 -> {
                    // U+0000, U+0080..U+07FF -> 110xxxxx 10xxxxxx
                    // 11 meaningful bits -> 2 bytes
                    buffer[writtenBytes++] = (char ushr 6) or 0b1100_0000
                    buffer[writtenBytes++] = (char and 0b0011_1111) or 0b1000_0000
                }
                else -> {
                    // U+0800..U+FFFF -> 1110xxxx 10xxxxxx 10xxxxxx
                    // 16 meaningful bits -> 3 bytes
                    buffer[writtenBytes++] = (char ushr 12) or 0b1110_0000
                    buffer[writtenBytes++] = ((char ushr 6) and 0b0011_1111) or 0b1000_0000
                    buffer[writtenBytes++] = (char and 0b0011_1111) or 0b1000_0000
                }
            }
        }

        return if (buffer.size == writtenBytes) buffer else buffer.copyOf(writtenBytes)
    }

    private inline fun allocateByteArray(string: String, stringLength: Int): ByteArray {
        val byteArraySize = if (stringLength < 32) {
            // Assumption: The majority of String literals are quite short strings.
            // We can calculate the exact amount of bytes the string will occupy.
            // This would help to avoid one `ByteArray.copyOf(Int)` call.
            var requiredBytes = 0

            var index = 0
            while (index < stringLength) {
                requiredBytes += when (string[index++]) {
                    in '\u0001'..'\u007f' -> 1
                    '\u0000', in '\u0080'..'\u07ff' -> 2
                    else -> 3
                }
            }

            requiredBytes
        } else {
            // Fallback to the worst case estimation.
            stringLength * 3
        }

        return ByteArray(byteArraySize)
    }

    fun decode(array: ByteArray): String {
        val arraySize = array.size
        if (arraySize == 0) return EMPTY_STRING

        val buffer =
            CharArray(arraySize) // Allocate for the worse case. Anyway String constructor will make a CharArray copy.
        var charsWritten = 0

        var index = 0
        while (index < arraySize) {
            val byte1 = array.readByteAsInt(index++)
            buffer[charsWritten++] = when {
                byte1 != 0 && byte1 and 0b1000_0000 == 0 -> {
                    // 0xxxxxxx -> U+0001..U+007F
                    // 1 byte -> 7 meaningful bits
                    byte1
                }
                byte1 ushr 5 == 0b000_0110 -> {
                    // 110xxxxx 10xxxxxx -> U+0080..U+07FF or U+0000
                    // 2 bytes -> 11 meaningful bits
                    if (index < arraySize) {
                        val byte2 = array.readByteAsInt(index)
                        if (isValidContinuation(byte2)) {
                            index++
                            ((byte1 and 0b0001_1111) shl 6) or (byte2 and 0b0011_1111)
                        } else {
                            // broken byte sequence
                            REPLACEMENT_CHAR
                        }
                    } else {
                        // unexpectedly interrupted byte sequence
                        REPLACEMENT_CHAR
                    }
                }
                byte1 ushr 4 == 0b0000_1110 -> {
                    // 1110xxxx 10xxxxxx 10xxxxxx -> U+0800..U+FFFF
                    // 3 bytes -> 16 meaningful bits
                    if (index < arraySize + 1) {
                        val byte2 = array.readByteAsInt(index)
                        if (isValidContinuation(byte2)) {
                            index++
                            val byte3 = array.readByteAsInt(index)
                            if (isValidContinuation(byte2)) {
                                index++
                                ((byte1 and 0b0000_1111) shl 12) or ((byte2 and 0b0011_1111) shl 6) or (byte3 and 0b0011_1111)
                            } else {
                                // broken byte sequence
                                REPLACEMENT_CHAR
                            }
                        } else {
                            // broken byte sequence
                            REPLACEMENT_CHAR
                        }
                    } else {
                        // unexpectedly interrupted byte sequence
                        REPLACEMENT_CHAR
                    }
                }
                else -> {
                    // unexpected bit pattern -> malformed
                    REPLACEMENT_CHAR
                }
            }
        }

        return if (buffer.size == charsWritten) String(buffer) else String(buffer, 0, charsWritten)
    }

    private inline fun String.readCharAsInt(index: Int): Int = this[index].toInt()

    private inline fun ByteArray.readByteAsInt(index: Int): Int = this[index].toInt() and 0b1111_1111

    private inline operator fun ByteArray.set(index: Int, value: Int) {
        this[index] = value.toByte()
    }

    private inline operator fun CharArray.set(index: Int, value: Int) {
        this[index] = value.toChar()
    }

    private inline fun isValidContinuation(byteN: Int) = byteN ushr 6 == 0b0000_0010

    private val EMPTY_BYTE_ARRAY = byteArrayOf()
    private const val EMPTY_STRING = ""

    private const val REPLACEMENT_CHAR = '?'.toInt()
}
