package homework6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MergeSortTest {
    companion object {
        @JvmStatic
        fun getArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                mutableListOf(2, 6, 7, 1, 3, 5, 0, 4),
                mutableListOf(0, 1, 2, 3, 4, 5, 6, 7)
            ),
            Arguments.of(
                mutableListOf(6, 5, 3, 4, 1, 0, 2, 7),
                mutableListOf(0, 1, 2, 3, 4, 5, 6, 7)
            ),
            Arguments.of(
                mutableListOf(8, 5, 2, 3, 4, 10, 9, 0, 1, 6, 7),
                mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            ),
            Arguments.of(
                mutableListOf(8, 3, 2, 3, 3, 10, 9, 0, 1, 6, 7),
                mutableListOf(0, 1, 2, 3, 3, 3, 6, 7, 8, 9, 10)
            ),
            Arguments.of(
                mutableListOf(1),
                mutableListOf(1)
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    fun testSort(testList: MutableList<Int>, expected: MutableList<Int>) {
        testList.mergeSort()
        assertEquals(expected, testList)
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    fun testMultiThreadMergeSort(testList: MutableList<Int>, expected: MutableList<Int>) {
        testList.multiThreadMergeSort(2)
        assertEquals(expected, testList)
    }
}
