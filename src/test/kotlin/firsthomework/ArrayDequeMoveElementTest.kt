package firsthomework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class ArrayDequeMoveElementTest {

    @Test
    fun testMoveSimpleElement() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        testArrayDeque.moveElement(indexFrom = 2, indexTo = 4)
        assertEquals("1, 2, 4, 5, 3", testArrayDeque.joinToString())
    }

    @Test
    fun testMoveComplexElement() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        testArrayDeque.moveElement(indexFrom = 2, indexTo = 4)
        testArrayDeque.moveElement(indexFrom = 1, indexTo = 1)
        testArrayDeque.moveElement(indexFrom = 0, indexTo = 3)
        assertEquals("2, 4, 5, 1, 3", testArrayDeque.joinToString())
    }

    @Test
    fun testMoveElementInEmptyDeque() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        assertThrows<ArrayIndexOutOfBoundsException> { testArrayDeque.moveElement(indexFrom = 0, indexTo = 2) }
    }

    @Test
    fun testMoveElementWithNegativeIndexFrom() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        assertThrows<ArrayIndexOutOfBoundsException> { testArrayDeque.moveElement(indexFrom = -2, indexTo = 0) }
    }

    @Test
    fun testMoveElementWithNegativeIndexTo() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        assertThrows<ArrayIndexOutOfBoundsException> { testArrayDeque.moveElement(indexFrom = 1, indexTo = -1) }
    }

    @Test
    fun testMoveElementWithInvalidIndexFrom() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        assertThrows<ArrayIndexOutOfBoundsException> { testArrayDeque.moveElement(indexFrom = 15, indexTo = 1) }
    }

    @Test
    fun testMoveElementWithInvalidIndexTo() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(1..5)
        assertThrows<ArrayIndexOutOfBoundsException> { testArrayDeque.moveElement(indexFrom = 1, indexTo = 10) }
    }
}
