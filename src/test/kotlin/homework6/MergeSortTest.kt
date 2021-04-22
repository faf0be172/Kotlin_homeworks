package homework6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MergeSortTest {
    companion object {
        @JvmStatic
        fun getLists(): Stream<Arguments> = Stream.of(
            Arguments.of(
                mutableListOf(2, 6, 7, 1, 3, 5, 0, 4),
                mutableListOf(0, 1, 2, 3, 4, 5, 6, 7),
                (0..4).toList()
            ),
            Arguments.of(
                mutableListOf(0, 8, 28, 2, 12, 26, 11, 9, 2, 28, 2, 9, 29, 8, 15, 11, 29, 14, 2, 6),
                mutableListOf(0, 2, 2, 2, 2, 6, 8, 8, 9, 9, 11, 11, 12, 14, 15, 26, 28, 28, 29, 29),
                (0..6).toList()
            ),
            Arguments.of(
                mutableListOf(29, 27, 21, 10, 17, 16, 13, 19, 10, 11, 15, 27, 4, 17, 29, 13, 24, 8, 0, 23),
                mutableListOf(0, 4, 8, 10, 10, 11, 13, 13, 15, 16, 17, 17, 19, 21, 23, 24, 27, 27, 29, 29),
                (0..6).toList()
            ),
            Arguments.of(
                mutableListOf(8, 3, 2, 3, 3, 10, 9, 0, 1, 6, 7),
                mutableListOf(0, 1, 2, 3, 3, 3, 6, 7, 8, 9, 10),
                (0..6).toList()
            ),
            Arguments.of(
                mutableListOf(1),
                mutableListOf(1),
                (0..3).toList()
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getLists")
    fun testSort(testList: MutableList<Int>, expected: MutableList<Int>, levels: List<Int>) {
        for (level in levels) {
            val sortedList = testList.sorted(level)
            assertEquals(expected, sortedList)
        }
    }
}
