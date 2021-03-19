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
    /*
    @Test
    fun testSerialization() {
        val path = "storageData.json"
        val testStorage = PerformedCommandStorage()
        for (number in listOf(1, 2, 3, 4, 5)) {
            PushBack(number).process(testStorage)
        }
        for (number in listOf(8, 7, 6)) {
            PushFront(number).process(testStorage)
        }
        MoveElement(indexFrom = 1, indexTo = 7).process(testStorage)
        MoveElement(indexFrom = 0, indexTo = 6).process(testStorage)
        MoveElement(indexFrom = 0, indexTo = 7).process(testStorage)
        testStorage.serializeActions(path)
        testStorage.undoLastAction()
        PushBack(value = 10).process(testStorage)

        val newTestStorage = PerformedCommandStorage()
        newTestStorage.deserializeActions(path)
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", newTestStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }*/
}
