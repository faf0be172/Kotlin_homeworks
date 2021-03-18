package firsthomework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class ArrayDequeMoveElementTest {

    @Test
    fun testMoveElement() {
        val testArrayDeque: ArrayDeque<Int> = ArrayDeque()
        testArrayDeque.addAll(listOf(1, 2, 3, 4, 5))
        testArrayDeque.moveElement(indexFrom = 2, indexTo = 4)
        assertEquals("1, 2, 4, 5, 3", testArrayDeque.joinToString())
        testArrayDeque.moveElement(indexFrom = 1, indexTo = 1)
        assertEquals("1, 2, 4, 5, 3", testArrayDeque.joinToString())
        testArrayDeque.moveElement(indexFrom = 0, indexTo = 3)
        assertEquals("2, 4, 5, 1, 3", testArrayDeque.joinToString())
    }
}