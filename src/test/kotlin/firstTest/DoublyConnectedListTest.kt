package firstTest

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class DoublyConnectedListTest {
    @Test
    fun simpleAdd() {
        val testList = DoublyConnectedList<Int>()
        testList.add(3)
        testList.add(4)
        assertEquals(listOf(3, 4), listOf(testList.get(0), testList.get(1)))
    }

    @Test
    fun simpleAddWithPosition() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.add("ccc", 0)
        assertEquals(listOf("ccc", "aaa", "bbb"), listOf(
            testList.get(0),
            testList.get(1),
            testList.get(2)
        )
        )
    }

    @Test
    fun simpleAddException() {
        val testList = DoublyConnectedList<Int>()
        testList.add(3)
        testList.add(4)
        assertEquals(listOf(3, 4), listOf(testList.get(0), testList.get(1)))
    }
}
