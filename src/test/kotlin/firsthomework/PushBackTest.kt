package firsthomework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class PushBackTest {

    @Test
    fun testPushBackSimple() {
        val testStorage = PerformedCommandStorage()
        PushBack(value = 1).process(testStorage)
        assertEquals(listOf(1), testStorage.arrayDeque)
    }

    @Test
    fun testPushBackComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..3) {
            PushBack(number).process(testStorage)
        }
        assertEquals((1..3).toList(), testStorage.arrayDeque)
    }

    @Test
    fun testUndoPushBackSimple() {
        val testStorage = PerformedCommandStorage()
        PushBack(value = 1).process(testStorage)
        testStorage.undoLastAction()
        assertTrue(testStorage.arrayDeque.isEmpty())
    }

    @Test
    fun testUndoPushBackComplicated() {
        val testStorage = PerformedCommandStorage()
        for (number in 1..3) {
            PushBack(number).process(testStorage)
        }
        testStorage.undoLastAction()
        assertEquals(listOf(1, 2), testStorage.arrayDeque)
    }
}
