package firsthomework

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PushFrontTest {

    @Test
    fun testPushFrontSimple() {
        val testStorage = PerformedCommandStorage()
        PushFront(value = 1).process(testStorage)
        assertEquals(listOf(1), testStorage.arrayDeque)
    }

    @Test
    fun testPushBackComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..3) {
            PushFront(number).process(testStorage)
        }
        assertEquals((1..3).toList().reversed(), testStorage.arrayDeque)
    }

    @Test
    fun testUndoPushFrontSimple() {
        val testStorage = PerformedCommandStorage()
        PushFront(value = 1).process(testStorage)
        testStorage.undoLastAction()
        assertTrue(testStorage.arrayDeque.isEmpty())
    }

    @Test
    fun testUndoPushFrontComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..3) {
            PushFront(number).process(testStorage)
        }
        testStorage.undoLastAction()
        assertEquals(listOf(2, 1), testStorage.arrayDeque)
    }
}
