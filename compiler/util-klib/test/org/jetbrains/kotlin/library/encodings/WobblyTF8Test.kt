/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.library.encodings

import org.junit.Assert.*
import org.junit.Test
import java.lang.Character.*

class WobblyTF8Test {
    // Empty string decoding and encoding by Wobbly and UTF8.
    @Test
    fun emptyString() {
        val emptyString = ""

        val encodedWithUTF8: ByteArray = emptyString.encodeWithUTF8()
        val encodedWithWobbly: ByteArray = emptyString.encodeWithWobbly()
        assertArrayEquals(encodedWithUTF8, encodedWithWobbly)

        val decodedWithUTF8: String = encodedWithUTF8.decodeWithUTF8()
        assertEquals(emptyString, decodedWithUTF8)
        val decodedWithWobbly: String = encodedWithWobbly.decodeWithWobbly()
        assertEquals(emptyString, decodedWithWobbly)
    }

    // Well-formed UTF16 string decoding and encoding by Wobbly and UTF8.
    @Test
    fun wellFormedString() {
        repeat(10) {
            val wellFormedString = generateWellFormedString(
                bmpCodePointsBeforeSurrogates = 10_000,
                bmpCodePointsAfterSurrogates = 10_000,
                supplementaryCodePoints = 80_000
            )

            val encodedWithUTF8: ByteArray = wellFormedString.encodeWithUTF8()
            val encodedWithWobbly: ByteArray = wellFormedString.encodeWithWobbly()
            assertArrayEquals(encodedWithUTF8, encodedWithWobbly)

            val decodedWithUTF8: String = encodedWithUTF8.decodeWithUTF8()
            assertEquals(wellFormedString, decodedWithUTF8)
            val decodedWithWobbly: String = encodedWithWobbly.decodeWithWobbly()
            assertEquals(wellFormedString, decodedWithWobbly)
        }
    }

    // Ill-formed string encoding by UTF8, then decoding it by Wobbly and UTF8.
    @Test
    fun illFormedStringEncodingByUTF8DecodingByWobbly() {
        repeat(10) {
            val illFormedString = generateIllFormedString(
                bmpCodePointsBeforeSurrogates = 10_000,
                isolatedSurrogates = 10_000,
                bmpCodePointsAfterSurrogates = 10_000,
                supplementaryCodePoints = 80_000
            )

            val encodedWithUTF8: ByteArray = illFormedString.encodeWithUTF8()

            val decodedWithUTF8: String = encodedWithUTF8.decodeWithUTF8()
            assertNotEquals(illFormedString, decodedWithUTF8)
            val decodedWithWobbly: String = encodedWithUTF8.decodeWithWobbly()
            assertEquals(decodedWithUTF8, decodedWithWobbly)
        }
    }

    // Ill-formed string lossless encoding and decoding by Wobbly.
    @Test
    fun illFormedStringEncodingByWobblyDecodingByWobbly() {
        repeat(10) {
            val illFormedString = generateIllFormedString(
                bmpCodePointsBeforeSurrogates = 10_000,
                isolatedSurrogates = 10_000,
                bmpCodePointsAfterSurrogates = 10_000,
                supplementaryCodePoints = 80_000
            )

            val encodedWithUTF8: ByteArray = illFormedString.encodeWithUTF8()
            val encodedWithWobbly: ByteArray = illFormedString.encodeWithWobbly()
            assertFalse(encodedWithUTF8.contentEquals(encodedWithWobbly))

            val decodedWithWobbly: String = encodedWithWobbly.decodeWithWobbly()
            assertEquals(illFormedString, decodedWithWobbly)
        }
    }

    private companion object {
        fun String.encodeWithWobbly(): ByteArray = WobblyTF8.encode(this)
        fun String.encodeWithUTF8(): ByteArray = toByteArray(Charsets.UTF_8)

        fun ByteArray.decodeWithWobbly(): String = WobblyTF8.decode(this)
        fun ByteArray.decodeWithUTF8(): String = toString(Charsets.UTF_8)

        val CODE_POINTS_BEFORE_SURROGATES = MIN_CODE_POINT until MIN_SURROGATE.toInt()
        val CODE_POINTS_ISOLATED_SURROGATES = MIN_SURROGATE.toInt()..MAX_SURROGATE.toInt()
        val CODE_POINTS_AFTER_SURROGATES = (MAX_SURROGATE.toInt() + 1) until MIN_SUPPLEMENTARY_CODE_POINT
        val CODE_POINTS_SUPPLEMENTARY = MIN_SUPPLEMENTARY_CODE_POINT..MAX_CODE_POINT

        fun generateWellFormedString(
            bmpCodePointsBeforeSurrogates: Int,
            bmpCodePointsAfterSurrogates: Int,
            supplementaryCodePoints: Int
        ): String {
            val codePoints = ArrayList<Int>(bmpCodePointsBeforeSurrogates + bmpCodePointsAfterSurrogates)
            repeat(bmpCodePointsBeforeSurrogates) { codePoints += CODE_POINTS_BEFORE_SURROGATES.random() }
            repeat(bmpCodePointsAfterSurrogates) { codePoints += CODE_POINTS_AFTER_SURROGATES.random() }
            repeat(supplementaryCodePoints) { codePoints += CODE_POINTS_SUPPLEMENTARY.random() }
            codePoints.shuffle()
            return buildString { codePoints.forEach(::appendCodePoint) }
        }

        fun generateIllFormedString(
            bmpCodePointsBeforeSurrogates: Int,
            isolatedSurrogates: Int,
            bmpCodePointsAfterSurrogates: Int,
            supplementaryCodePoints: Int
        ): String {
            val codePoints = ArrayList<Int>(bmpCodePointsBeforeSurrogates + isolatedSurrogates + bmpCodePointsAfterSurrogates)
            repeat(bmpCodePointsBeforeSurrogates) { codePoints += CODE_POINTS_BEFORE_SURROGATES.random() }
            repeat(isolatedSurrogates) { codePoints += CODE_POINTS_ISOLATED_SURROGATES.random() }
            repeat(bmpCodePointsAfterSurrogates) { codePoints += CODE_POINTS_AFTER_SURROGATES.random() }
            repeat(supplementaryCodePoints) { codePoints += CODE_POINTS_SUPPLEMENTARY.random() }
            codePoints.shuffle()

            return buildString {
                // need to guarantee that at least one isolated surrogate is present
                appendCodePoint(CODE_POINTS_ISOLATED_SURROGATES.random())
                appendCodePoint(CODE_POINTS_BEFORE_SURROGATES.random())

                codePoints.forEach(::appendCodePoint)
            }
        }
    }
}
