package homework1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class SerializationTest() {

    @Test
    fun testDeserialization() {
        val testStorage = PerformedCommandStorage()
        val path = javaClass.getResource("actionsToLoad.json").path
        testStorage.deserializeActions(path)
        assertEquals((1..4).toList(), testStorage.arrayDeque)
    }

    @Test
    fun testSerialization(@TempDir tempJsonFile: File) {
        val testStorage = PerformedCommandStorage()
        for (number in 1..4) {
            PushBack(number).process(testStorage)
        }
        PushFront(value = 5).process(testStorage)
        MoveElement(indexFrom = 0, indexTo = 4).process(testStorage)
        val sampleJsonPath = javaClass.getResource("actionsToSaveSample.json").path
        val jsonFile = tempJsonFile.resolve("temporary.json")

        testStorage.serializeActions(jsonFile.path)
        assertEquals(File(sampleJsonPath).readText(), jsonFile.readText())
        jsonFile.delete()
    }

    @Test
    fun testComplexSerialization(@TempDir tempJsonFile: File) {
        val testStorage = PerformedCommandStorage()
        for (number in 1..5) {
            PushBack(number).process(testStorage)
        }
        for (number in listOf(8, 7, 6)) {
            PushFront(number).process(testStorage)
        }
        MoveElement(indexFrom = 1, indexTo = 7).process(testStorage)
        MoveElement(indexFrom = 0, indexTo = 6).process(testStorage)
        MoveElement(indexFrom = 0, indexTo = 7).process(testStorage)

        val jsonFile = tempJsonFile.resolve("temporary.json")
        testStorage.serializeActions(jsonFile.path)
        testStorage.undoLastAction()
        PushBack(value = 10).process(testStorage)

        val newTestStorage = PerformedCommandStorage()
        newTestStorage.deserializeActions(jsonFile.path)
        assertEquals((1..8).toList(), newTestStorage.arrayDeque)
        jsonFile.delete()
    }
}
