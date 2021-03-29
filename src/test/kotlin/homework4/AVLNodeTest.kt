package homework4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class AVLNodeTest {
    companion object {
        @JvmStatic
        fun getDefaultArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(5, "555"),
            Arguments.of(3, "333"),
            Arguments.of(4, "444"),
            Arguments.of(2, "222"),
            Arguments.of(6, "666"),
            Arguments.of(7, null)
        )

        @JvmStatic
        fun getKeysArguments():Stream<Arguments> = Stream.of(
            Arguments.of(6, true),
            Arguments.of(3, true),
            Arguments.of(4, true),
            Arguments.of(8, false),
            Arguments.of(1, false),
            Arguments.of(5, false),
        )

        @JvmStatic
        fun getValuesArguments():Stream<Arguments> = Stream.of(
            Arguments.of("666", true),
            Arguments.of("333", true),
            Arguments.of("444", true),
            Arguments.of("888", false),
            Arguments.of("111", false),
            Arguments.of("555", false),
        )
    }

    @ParameterizedTest
    @MethodSource("getDefaultArguments")
    fun `test recursive get`(key: Int, expectedValue: String?) {
        val nodeA = AVLNode(5, "555")
        val nodeB = AVLNode(3, "333")
        val nodeC = AVLNode(4, "444")
        val nodeD = AVLNode(2, "222")
        val nodeE = AVLNode(6, "666")
        nodeA.leftChild = nodeB
        nodeA.rightChild = nodeE
        nodeB.leftChild = nodeD
        nodeB.rightChild = nodeC
        assertEquals(expectedValue, nodeA.recursiveGet(key))
    }

    @ParameterizedTest
    @MethodSource("getKeysArguments")
    fun `test recursive contains value`(key: Int, expected: Boolean) {
        val nodeA = AVLNode(6, "666")
        val nodeB = AVLNode(3, "333")
        val nodeC = AVLNode(4, "444")
        val nodeD = AVLNode(2, "222")
        val nodeE = AVLNode(7, "777")
        nodeA.leftChild = nodeB
        nodeA.rightChild = nodeE
        nodeB.leftChild = nodeD
        nodeB.rightChild = nodeC
        assertEquals(expected, nodeA.recursiveContainsKey(key))
    }

    @ParameterizedTest
    @MethodSource("getValuesArguments")
    fun `test recursive contains key`(value: String, expected: Boolean) {
        val nodeA = AVLNode(6, "666")
        val nodeB = AVLNode(3, "333")
        val nodeC = AVLNode(4, "444")
        val nodeD = AVLNode(2, "222")
        val nodeE = AVLNode(7, "777")
        nodeA.leftChild = nodeB
        nodeA.rightChild = nodeE
        nodeB.leftChild = nodeD
        nodeB.rightChild = nodeC
        assertEquals(expected, nodeA.recursiveContainsValue(value))
    }

    @Test
    fun `test recursive put`() {
        val nodeA = AVLNode(6, "666")
        nodeA.recursivePut(4, "444")
        nodeA.recursivePut(8, "888")
        nodeA.recursivePut(7, "777")
        assertTrue(
            nodeA.leftChild?.key == 4 &&
            nodeA.rightChild?.key == 8 &&
            nodeA.rightChild?.leftChild?.key == 7)
    }

    @Test
    fun `test balance sub tree`() {
        val testTree = AVLTree<Int, String>()
        testTree.put(6, "666")
        testTree.put(8, "888")
        testTree.put(7, "777")
        testTree.put(9, "999")
        testTree.put(10, "101010")
        testTree.put(11, "111111")
        testTree.put(12, "121212")
        testTree.put(13, "131313")
        testTree.put(14, "141414")

        assertEquals(11, testTree.root?.key)
    }

    @Test
    fun `test recursive remove`() {
        val testTree = AVLTree<Int, String>()
        testTree.put(4, "444")
        testTree.put(6, "666")
        testTree.put(8, "888")
        testTree.put(7, "777")
        testTree.put(9, "999")
        testTree.put(10, "101010")
        testTree.put(11, "111111")
        testTree.removeKey(9)
        testTree.removeKey(4)
        assertTrue(testTree.root?.rightChild?.key == 10 && testTree.root?.leftChild?.key == 6)
    }
}
