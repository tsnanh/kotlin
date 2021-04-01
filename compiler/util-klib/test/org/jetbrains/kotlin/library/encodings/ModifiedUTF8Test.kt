/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.library.encodings

import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

@ExperimentalUnsignedTypes
class ModifiedUTF8Test {
    @Test
    fun testEmpty() {
        val string = ""
        val bytesExpected = byteArrayOf()

        val bytesModifiedUTF8 = ModifiedUTF8.encode(string)
        val bytesDataOutput = string.writeUTFStringViaDataOutput()
        assertArrayEquals(bytesExpected, bytesModifiedUTF8)
        assertArrayEquals(bytesDataOutput, bytesModifiedUTF8)

        assertEquals(string, ModifiedUTF8.decode(bytesModifiedUTF8))
    }

    @Test
    fun testNull() {
        val string = NULL_CHAR.toString()
        val bytesExpected = byteArrayOf(0b1100_0000.toByte(), 0b1000_0000.toByte())

        val bytesModifiedUTF8 = ModifiedUTF8.encode(string)
        val bytesDataOutput = string.writeUTFStringViaDataOutput()
        assertArrayEquals(bytesExpected, bytesModifiedUTF8)
        assertArrayEquals(bytesDataOutput, bytesModifiedUTF8)

        assertEquals(string, ModifiedUTF8.decode(bytesModifiedUTF8))
    }

    @Test
    fun testAscii() {
        repeat(10) {
            val string = ASCII_CHARS.shuffled().joinToString("")
            assertEquals(ASCII_CHARS.count(), string.length)

            val bytesModifiedUTF8 = ModifiedUTF8.encode(string)
            assertEquals(string.length, bytesModifiedUTF8.size)

            val bytesDataOutput = string.writeUTFStringViaDataOutput()
            val bytesTraditionalUTF8 = string.toByteArray(Charsets.UTF_8)
            assertArrayEquals(bytesDataOutput, bytesModifiedUTF8)
            assertArrayEquals(bytesTraditionalUTF8, bytesModifiedUTF8)

            assertEquals(string, ModifiedUTF8.decode(bytesModifiedUTF8))
        }
    }

    @Test
    fun testMultiByteChars() {
        assertEquals(2, ModifiedUTF8.encode(TWO_BYTE_CHARS.first.toString()).size)
        assertEquals(2, ModifiedUTF8.encode(TWO_BYTE_CHARS.last.toString()).size)
        assertEquals(3, ModifiedUTF8.encode(THREE_BYTE_CHARS.first.toString()).size)
        assertEquals(3, ModifiedUTF8.encode(THREE_BYTE_CHARS.last.toString()).size)
    }

    @Test
    fun testMisc() {
        repeat(10) {
            val chars = mutableListOf<Char>()
            var expectedByteCount = 0

            chars += NULL_CHAR; expectedByteCount += 2
            repeat(100) { chars += ASCII_CHARS.random(); expectedByteCount += 1 }
            repeat(1000) { chars += TWO_BYTE_CHARS.random(); expectedByteCount += 2 }
            repeat(1000) { chars += THREE_BYTE_CHARS.random(); expectedByteCount += 3 }

            val string = chars.shuffled().joinToString("")

            val bytesModifiedUTF8 = ModifiedUTF8.encode(string)
            assertEquals(expectedByteCount, bytesModifiedUTF8.size)

            val bytesDataOutput = string.writeUTFStringViaDataOutput()
            assertArrayEquals(bytesDataOutput, bytesModifiedUTF8)

            assertEquals(string, ModifiedUTF8.decode(bytesModifiedUTF8))
        }
    }

    private fun String.writeUTFStringViaDataOutput(): ByteArray {
        ByteArrayOutputStream(length * 3 + 2).use { output ->
            DataOutputStream(output).writeUTF(this)
            val writtenData = output.toByteArray()
            val effectiveSize = DataInputStream(ByteArrayInputStream(writtenData)).use { it.readUnsignedShort() }
            assertTrue(effectiveSize >= length)
            assertEquals(writtenData.size - 2, effectiveSize)
            return if (effectiveSize == 0) byteArrayOf() else writtenData.copyOfRange(2, effectiveSize + 2)
        }
    }

    companion object {
        const val NULL_CHAR = '\u0000'
        val ASCII_CHARS = '\u0001'..'\u007f'
        val TWO_BYTE_CHARS = '\u0080'..'\u07ff'
        val THREE_BYTE_CHARS = '\u0800'..Char.MAX_VALUE
    }
}
