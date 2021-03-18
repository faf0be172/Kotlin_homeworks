package firsthomework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class PerformedCommandStorageTest {

    @Test
    fun testPrintEmptyPlacement() {
        val testStorage = PerformedCommandStorage()
        assertEquals("[]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testUndoEmptyActionsStack() {
        val testStorage = PerformedCommandStorage()
        testStorage.undoLastAction()
        assertEquals("[]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testAddPushBackAction() {
        val testStorage = PerformedCommandStorage()
        PushBack(value = 1).process(testStorage)
        assertEquals("[1]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
        PushBack(value = 2).process(testStorage)
        assertEquals("[1, 2]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testUndoPushBackAction() {
        val testStorage = PerformedCommandStorage()
        PushBack(value = 1).process(testStorage)
        testStorage.undoLastAction()
        assertEquals("[]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))

        PushBack(value = 1).process(testStorage)
        PushBack(value = 2).process(testStorage)
        testStorage.undoLastAction()
        assertEquals("[1]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testAddPushFrontAction() {
        val testStorage = PerformedCommandStorage()
        PushFront(value = 1).process(testStorage)
        assertEquals("[1]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
        PushFront(value = 2).process(testStorage)
        assertEquals("[2, 1]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testUndoPushFrontAction() {
        val testStorage = PerformedCommandStorage()
        PushFront(value = 1).process(testStorage)
        testStorage.undoLastAction()
        assertEquals("[]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))

        PushFront(value = 1).process(testStorage)
        PushFront(value = 2).process(testStorage)
        testStorage.undoLastAction()
        assertEquals("[1]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testAddMoveElementAction() {
        val testStorage = PerformedCommandStorage()
        for (number in listOf(1, 2, 3, 4, 5)) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 1, indexTo = 4).process(testStorage)
        assertEquals("[1, 3, 4, 5, 2]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

    @Test
    fun testUndoMoveElementAction() {
        val testStorage = PerformedCommandStorage()
        for (number in listOf(1, 2, 3, 4, 5)) {
            PushBack(number).process(testStorage)
        }
        MoveElement(indexFrom = 1, indexTo = 4).process(testStorage)
        testStorage.undoLastAction()
        assertEquals("[1, 2, 3, 4, 5]", testStorage.arrayDeque.joinToString(prefix = "[", postfix = "]"))
    }

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
    }
}
