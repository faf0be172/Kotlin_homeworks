package retestFinalTest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

internal class ByteArrayExtensionsTest {
    @Test
    fun simpleCompress() {
        val testArray = byteArrayOf(1, 3, 4, 4, 4, 4, 4)
        assertEquals(
            byteArrayOf(1, 1, 1, 3, 5, 4).toList(),
            testArray.compressed().toList()
        )
    }

    @Test
    fun complexCompress() {
        val testArray = byteArrayOf(1, 3, 3, 3, 2, 4, 4, 4, 7, 7, 9, 0, -1, -1, 2, 'a'.toByte(), 'a'.toByte())
        assertEquals(
            byteArrayOf(1, 1, 3, 3, 1, 2, 3, 4, 2, 7, 1, 9, 1, 0, 2, -1, 1, 2, 2, 97).toList(),
            testArray.compressed().toList()
        )
    }

    @Test
    fun bigValueInARowExtension() {
        val testArray = ByteArray(150) { 1 }
        assertThrows<IllegalArgumentException> {
            testArray.compressed()
        }
    }

    @Test
    fun simpleDecompress() {
        val testArray = byteArrayOf(1, 1, 1, 3, 5, 4)
        assertEquals(
            byteArrayOf(1, 3, 4, 4, 4, 4, 4).toList(),
            testArray.decompressed().toList()
        )
    }

    @Test
    fun complexDecompress() {
        val testArray = byteArrayOf(1, 1, 3, 3, 1, 2, 3, 4, 2, 7, 1, 9, 1, 0, 2, -1, 1, 2, 2, 97)
        assertEquals(
            byteArrayOf(1, 3, 3, 3, 2, 4, 4, 4, 7, 7, 9, 0, -1, -1, 2, 'a'.toByte(), 'a'.toByte()).toList(),
            testArray.decompressed().toList()
        )
    }

    @Test
    fun notEvenArray() {
        val testArray = byteArrayOf(1, 1, 1, 3, 5, 4, 3)
        assertThrows<IllegalStateException> {
            testArray.decompressed()
        }
    }

    @Test
    fun complexTest() {
        val testArray = byteArrayOf(1, 3, 3, 3, 2, 4, 4, 4, 7, 7, 9, 0, -1, -1, 2, 'a'.toByte(), 'a'.toByte())
        val compressedArray = testArray.compressed()
        assertEquals(
            testArray.toList(),
            compressedArray.decompressed().toList()
        )
    }
}
