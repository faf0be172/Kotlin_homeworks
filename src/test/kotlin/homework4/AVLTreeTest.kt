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

    @DisplayName("Remove from empty map")
    @Test
    fun testRemoveFromEmptyMap() {
        val testMap: AVLTree <String, String> = AVLTree()
        assertEquals(null, testMap.removeKey("nonExistentKey"))
    }

    @DisplayName("Get keys")
    @Test
    fun testGetKeys() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(listOf("a", "b"), testMap.getKeys())
    }

    @DisplayName("Change existing node value")
    @Test
    fun testChangeNodeValue() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("a", "bbb")
        assertEquals("bbb", testMap.get("a"))
    }

    @DisplayName("Get values")
    @Test
    fun testGetValues() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(listOf("aaa", "bbb"), testMap.getValues())
    }

    @DisplayName("Get entries")
    @Test
    fun testGetEntries() {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(listOf(Pair("a", "aaa"), Pair("b", "bbb")), testMap.getEntries())
    }

    @Test
    fun testPutAllLacking() {
        val testMap1: AVLTree <String, String> = AVLTree()
        val testMap2: AVLTree <String, String> = AVLTree()
        testMap1.put("a", "aaa")
        testMap2.put("b", "bbb")
        testMap1.putAllLacking(testMap2)
        assertEquals("bbb", testMap1.get("b"))
    }

    @DisplayName("Contains key")
    @ParameterizedTest
    @MethodSource("getContainableKeyArguments")
    fun testContainsKey(key: Int, expectedValue: Boolean) {
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
    fun testContainsValue(value: String, expectedValue: Boolean) {
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
    fun testPutAndGet(key: String, expectedValue: Any?) {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(expectedValue, testMap.get(key))
    }
}
