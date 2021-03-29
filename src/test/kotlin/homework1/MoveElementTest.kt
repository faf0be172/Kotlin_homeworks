package homework1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MoveElementTest {
    @Test
    fun testMoveElementSimple() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 1, indexTo = 4).process(testStorage)
        assertEquals(listOf(1, 3, 4, 5, 2), testStorage.arrayDeque)
    }

    @Test
    fun testMoveElementComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 2, indexTo = 4).process(testStorage)
        MoveElement(indexFrom = 1, indexTo = 3).process(testStorage)
        MoveElement(indexFrom = 3, indexTo = 0).process(testStorage)
        assertEquals(listOf(2, 1, 4, 5, 3), testStorage.arrayDeque)
    }

    @Test
    fun testUndoMoveElementSimple() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 3, indexTo = 2).process(testStorage)
        testStorage.undoLastAction()
        assertEquals((1..5).toList(), testStorage.arrayDeque)
    }

    @Test
    fun testUndoMoveElementComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 2, indexTo = 4).process(testStorage)
        MoveElement(indexFrom = 1, indexTo = 3).process(testStorage)
        testStorage.undoLastAction()
        MoveElement(indexFrom = 0, indexTo = 1).process(testStorage)
        assertEquals(listOf(2, 1, 4, 5, 3), testStorage.arrayDeque)
    }

    @Test
    fun testMoveElementInStorage() {
        val testStorage = PerformedCommandStorage()
        assertThrows<ArrayIndexOutOfBoundsException> { testStorage.arrayDeque.moveElement(indexFrom = 0, indexTo = 2) }
    }

    @Test
    fun testMoveElementWithNegativeIndexFrom() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        assertThrows<ArrayIndexOutOfBoundsException> { testStorage.arrayDeque.moveElement(indexFrom = -2, indexTo = 0) }
    }

    @Test
    fun testMoveElementWithNegativeIndexTo() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        assertThrows<ArrayIndexOutOfBoundsException> { testStorage.arrayDeque.moveElement(indexFrom = 1, indexTo = -1) }
    }

    @Test
    fun testMoveElementWithInvalidIndexFrom() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        assertThrows<ArrayIndexOutOfBoundsException> { testStorage.arrayDeque.moveElement(indexFrom = 15, indexTo = 1) }
    }

    @Test
    fun testMoveElementWithInvalidIndexTo() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        assertThrows<ArrayIndexOutOfBoundsException> { testStorage.arrayDeque.moveElement(indexFrom = 1, indexTo = 10) }
    }
}
