package firsthomework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class PerformedCommandStorageTest {

    @Test
    fun testEmptyStorage() {
        val testStorage = PerformedCommandStorage()
        assertTrue(testStorage.arrayDeque.isEmpty())
    }

    @Test
    fun testUndoEmptyActionsStack() {
        val testStorage = PerformedCommandStorage()
        testStorage.undoLastAction()
        assertTrue(testStorage.arrayDeque.isEmpty())
    }
}
