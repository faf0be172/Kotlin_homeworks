package firstTest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class DoublyConnectedListTest {
    @Test
    fun emptyList() {
        val testList = DoublyConnectedList<Int>()
        assertTrue(testList.isEmpty())
        testList.add(1)
        assertTrue(!testList.isEmpty())
    }

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
    fun simpleAddWithPositionException() {
        val testList = DoublyConnectedList<Int>()
        assertThrows<IllegalArgumentException> {
            testList.add(-1, 10)
        }
    }

    @Test
    fun simpleRemoveTail() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.remove(1)
        assertEquals("aaa", testList.get(0))
    }

    @Test
    fun simpleRemoveHead() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.remove(0)
        assertEquals("bbb", testList.get(0))
    }

    @Test
    fun complexRemove() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.add("ccc", 0)
        testList.remove(1)
        assertEquals(listOf("ccc", "bbb"), listOf(testList.get(0), testList.get(1)))
    }

    @Test
    fun removeArgumentException() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.add("ccc", 0)
        assertThrows<IllegalArgumentException> {
            testList.remove(10)
        }
        assertThrows<IllegalArgumentException> {
            testList.remove(-1)
        }
    }

    @Test
    fun removeStateException() {
        val testList = DoublyConnectedList<String>()
        assertThrows<IllegalStateException> {
            testList.remove(0)
        }
    }

    @Test
    fun simpleGet() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.add("ccc", 0)
        testList.remove(1)
        assertEquals("ccc", testList.get())
    }

    @Test
    fun getArgumentException() {
        val testList = DoublyConnectedList<String>()
        testList.add("aaa", 0)
        testList.add("bbb", 1)
        testList.add("ccc", 0)
        assertThrows<IllegalArgumentException> {
            testList.get(10)
        }
        assertThrows<IllegalArgumentException> {
            testList.get(-1)
        }
    }

    @Test
    fun getAStateException() {
        val testList = DoublyConnectedList<String>()
        assertThrows<IllegalStateException> {
            testList.get(0)
        }
    }
}
