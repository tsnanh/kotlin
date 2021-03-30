/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.library.encodings

import java.io.DataOutput
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import java.nio.charset.CharsetEncoder
import java.nio.charset.CoderResult

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
object ModifiedUTF8 : Charset("MODIFIED-UTF8", null) {
    override fun contains(cs: Charset?) = false
    override fun newEncoder(): CharsetEncoder = Encoder()
    override fun newDecoder(): CharsetDecoder = Decoder()

    private class Encoder : CharsetEncoder(ModifiedUTF8, AVG_BYTES_PER_CHAR, MAX_BYTES_PER_CHAR, DEFAULT_REPLACEMENT) {
        override fun encodeLoop(src: CharBuffer, dst: ByteBuffer): CoderResult {
            var mark = src.position()
            try {
                while (src.hasRemaining()) {
                    val ch = src.get().toInt()
                    when {
                        ch != 0 && ch < 0x80 -> {
                            // U+0001..U+007F -> 0xxxxxxx
                            // 7 meaningful bits -> 1 byte
                            if (!dst.hasRemaining()) return CoderResult.OVERFLOW
                            dst += ch
                        }
                        ch < 0x0800 -> {
                            // U+0000, U+0080..U+07FF -> 110xxxxx 10xxxxxx
                            // 11 meaningful bits -> 2 bytes
                            if (dst.remaining() < 2) return CoderResult.OVERFLOW
                            dst += (ch ushr 6) or 0b1100_0000
                            dst += (ch and 0b0011_1111) or 0b1000_0000
                        }
                        else -> {
                            // U+0800..U+FFFF -> 1110xxxx 10xxxxxx 10xxxxxx
                            // 16 meaningful bits -> 3 bytes
                            if (dst.remaining() < 3) return CoderResult.OVERFLOW
                            dst += (ch ushr 12) or 0b1110_0000
                            dst += ((ch ushr 6) and 0b0011_1111) or 0b1000_0000
                            dst += (ch and 0b0011_1111) or 0b1000_0000
                        }
                    }
                    mark++
                }
                return CoderResult.UNDERFLOW
            } finally {
                src.position(mark) // set buffer position to the last processed character
            }
        }

        override fun isLegalReplacement(replacement: ByteArray) = replacement.singleOrNull() == DEFAULT_REPLACEMENT_BYTE
        override fun canEncode(c: Char) = true
        override fun canEncode(cs: CharSequence?) = true

        private operator fun ByteBuffer.plusAssign(value: Int) {
            put(value.toByte())
        }

        companion object {
            private const val AVG_BYTES_PER_CHAR = 1.1f
            private const val MAX_BYTES_PER_CHAR = 3f
            private const val DEFAULT_REPLACEMENT_BYTE = '?'.toByte() // though never used
            private val DEFAULT_REPLACEMENT = byteArrayOf(DEFAULT_REPLACEMENT_BYTE)
        }
    }

    private class Decoder : CharsetDecoder(ModifiedUTF8, AVG_CHARS_PER_BYTE, MAX_CHARS_PER_BYTE) {
        override fun decodeLoop(src: ByteBuffer, dst: CharBuffer): CoderResult {
            var mark = src.position()
            val limit = src.limit()
            try {
                while (mark < limit) {
                    val byte1 = src.get().toPositiveInt()
                    when {
                        byte1 != 0 && byte1 and 0b1000_0000 == 0 -> {
                            // 0xxxxxxx -> U+0001..U+007F
                            // 1 byte -> 7 meaningful bits
                            if (!dst.hasRemaining()) return CoderResult.OVERFLOW
                            dst += byte1
                            mark++
                        }
                        byte1 ushr 5 == 0b000_0110 -> {
                            // 110xxxxx 10xxxxxx -> U+0080..U+07FF or U+0000
                            // 2 bytes -> 11 meaningful bits
                            if (limit - mark < 2) return CoderResult.UNDERFLOW
                            if (!dst.hasRemaining()) return CoderResult.OVERFLOW
                            val byte2 = src.get().toPositiveInt()
                            if (!isValidContinuation(byte2)) return CoderResult.malformedForLength(1) // [byte1] is malformed input
                            dst += ((byte1 and 0b0001_1111) shl 6) or (byte2 and 0b0011_1111)
                            mark += 2
                        }
                        byte1 ushr 4 == 0b0000_1110 -> {
                            // 1110xxxx 10xxxxxx 10xxxxxx -> U+0800..U+FFFF
                            // 3 bytes -> 16 meaningful bits
                            if (limit - mark < 3) return CoderResult.UNDERFLOW
                            if (!dst.hasRemaining()) return CoderResult.OVERFLOW
                            val byte2 = src.get().toPositiveInt()
                            if (!isValidContinuation(byte2)) return CoderResult.malformedForLength(1) // [byte1] is malformed input
                            val byte3 = src.get().toPositiveInt()
                            if (!isValidContinuation(byte3)) return CoderResult.malformedForLength(2) // [byte1, byte2] is malformed input
                            dst += ((byte1 and 0b0000_1111) shl 12) or ((byte2 and 0b0011_1111) shl 6) or (byte3 and 0b0011_1111)
                            mark +=3
                        }
                        else -> {
                            // unexpected bit pattern -> malformed
                            return CoderResult.malformedForLength(1) // [byte1] is malformed input
                        }
                    }
                }

                return CoderResult.UNDERFLOW
            } finally {
                src.position(mark) // set buffer position to the last processed character
            }
        }

        private fun isValidContinuation(continuation: Int) = continuation ushr 6 == 0b0000_0010

        /**
         * Converts [Byte] to [Int] filling in most significant 24 bits with zeroes. The original [Byte.toInt] function
         * fills most significant bits with the sign bit of [Byte] value, which is undesired.
         */
        private fun Byte.toPositiveInt(): Int = toInt() and 0b1111_1111

        private operator fun CharBuffer.plusAssign(value: Int) {
            put(value.toChar())
        }

        companion object {
            private const val AVG_CHARS_PER_BYTE = 1f
            private const val MAX_CHARS_PER_BYTE = 1f
        }
    }
}
