package test1

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BorTreeTest {
    companion object {
        @JvmStatic
        fun getStringsToAdd(): Stream<Arguments> = Stream.of(
            Arguments.of("he", true),
            Arguments.of("she", true),
            Arguments.of("it", false)
        )
    }
    @ParameterizedTest
    @MethodSource("getStringsToAdd")
    fun testContains(value: String, expected: Boolean) {
        val testBorTree = BorTree()
        testBorTree.add("he")
        testBorTree.add("she")
        testBorTree.add("his")
        testBorTree.add("hers")
        assertEquals(expected, testBorTree.contains(value))
    }

    @Test
    fun testRemove() {
        val testBorTree = BorTree()
        testBorTree.add("he")
        testBorTree.add("she")
        testBorTree.add("his")
        testBorTree.add("hers")
        testBorTree.remove("his")
        assertEquals(false, testBorTree.contains("his"))
        assertEquals(true, testBorTree.contains("hers"))
    }

    @Test
    fun testFindByPrefix() {
        val testBorTree = BorTree()
        testBorTree.add("he")
        testBorTree.add("she")
        testBorTree.add("his")
        testBorTree.add("hers")
        assertEquals(1, testBorTree.howManyStartWithPrefix("s"))
    }
}
