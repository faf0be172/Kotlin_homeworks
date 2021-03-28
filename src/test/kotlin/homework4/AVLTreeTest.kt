package homework4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class AVLTreeTest {
    companion object {
        @JvmStatic
        fun getInsertableStringArguments(): Stream<Arguments> = Stream.of(
            Arguments.of("a", "aaa"),
            Arguments.of("b", "bbb"),
            Arguments.of("c", null)
        )

        @JvmStatic
        fun getContainableKeyArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(1, true),
            Arguments.of(5, false),
            Arguments.of(10, false),
            Arguments.of(7, true)
        )

        @JvmStatic
        fun getContainableValueArguments(): Stream<Arguments> = Stream.of(
            Arguments.of("5", true),
            Arguments.of("4", false),
            Arguments.of("10", false),
            Arguments.of("3", true)
        )
    }

    @Test
    fun `test remove from empty map`() {
        val testMap: AVLTree <String, String> = AVLTree()
        assertEquals(null, testMap.removeKey("nonExistentKey"))
    }

    @Test
    fun `test isEmpty function`() {
        val testMap: AVLTree <String, String> = AVLTree()
        val isMapEmpty = testMap.isEmpty()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertTrue(isMapEmpty && !testMap.isEmpty())
    }

    @Test
    fun `test clear function`() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        testMap.clear()
        assertTrue(testMap.isEmpty())
    }

    @Test
    fun `test get keys`() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(setOf("a", "b"), testMap.keys)
    }

    @Test
    fun `test change existing node value`() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("a", "bbb")
        assertEquals("bbb", testMap["a"])
    }

    @Test
    fun `test get values`() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(setOf("aaa", "bbb"), testMap.values)
    }

    @Test
    fun `test get entries`() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        val actualSet: MutableSet<Pair<String, String>> = mutableSetOf()
        for (entry in testMap.entries) actualSet.add(entry.toPair())
        assertEquals(setOf(Pair("a", "aaa"), Pair("b", "bbb")), actualSet)
    }

    @Test
    fun `test tree size`() {
        val testMap: AVLTree <Int, String> = AVLTree()
        testMap.put(2, "2")
        testMap.put(7, "7")
        testMap.put(8, "8")
        testMap.put(3, "3")
        testMap.put(5, "5")
        testMap.put(1, "1")
        assertEquals(6, testMap.size)
    }

    @ParameterizedTest
    @MethodSource("getContainableKeyArguments")
    fun `test remove`(key: Int, expectedValue: Boolean) {
        val testMap: AVLTree <Int, String> = AVLTree()
        testMap.put(2, "2")
        testMap.put(7, "7")
        testMap.put(8, "8")
        testMap.put(3, "3")
        testMap.put(5, "5")
        testMap.put(1, "1")
        testMap.removeKey(8)
        testMap.removeKey(5)
        testMap.removeKey(10)
        assertEquals(expectedValue, testMap.containsKey(key))
    }

    @DisplayName("Contains key")
    @ParameterizedTest
    @MethodSource("getContainableKeyArguments")
    fun `test contains key`(key: Int, expectedValue: Boolean) {
        val testMap: AVLTree <Int, String> = AVLTree()
        testMap.put(1, "1")
        testMap.put(6, "6")
        testMap.put(9, "9")
        testMap.put(3, "3")
        testMap.put(7, "7")
        testMap.put(2, "2")
        assertEquals(expectedValue, testMap.containsKey(key))
    }

    @DisplayName("Contains value")
    @ParameterizedTest
    @MethodSource("getContainableValueArguments")
    fun `test contains value`(value: String, expectedValue: Boolean) {
        val testMap: AVLTree <Int, String> = AVLTree()
        testMap.put(2, "2")
        testMap.put(5, "5")
        testMap.put(8, "8")
        testMap.put(3, "3")
        testMap.put(7, "7")
        testMap.put(1, "1")
        assertEquals(expectedValue, testMap.containsValue(value))
    }

    @DisplayName("Nullable and non-null queries")
    @ParameterizedTest
    @MethodSource("getInsertableStringArguments")
    fun `test put and get`(key: String, expectedValue: Any?) {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(expectedValue, testMap[key])
    }
}
