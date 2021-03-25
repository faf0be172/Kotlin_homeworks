package homework4

import org.junit.jupiter.api.Assertions.*
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
    }
    @Test
    fun testRemoveFromEmptyMap() {
        val testMap: AVLTree <String, String> = AVLTree()
        assertEquals(null, testMap.removeKey("nonExistentKey"))
    }

    @ParameterizedTest
    @MethodSource("getInsertableStringArguments")
    fun testPutAndGet(key: String, expectedValue: Any?) {
        val testMap: AVLTree <String, String> = AVLTree()
        testMap.put("a", "aaa")
        testMap.put("b", "bbb")
        assertEquals(expectedValue, testMap.get(key))
    }
}
