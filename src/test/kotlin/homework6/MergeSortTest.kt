package homework6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.random.nextInt

internal class MergeSortTest {
    companion object {
        private fun getLists(listsNumber: Int = 5, listSize: Int = 20, range: IntRange = 0..30) = List(listsNumber) {
            List(listSize) { Random.nextInt(range)}
        }

        @JvmStatic
        fun getArguments(): Stream<Arguments> {
            val randomLists = getLists()
            val stream = Stream.builder<Arguments>()
            for (list in randomLists) {
                for (level in 0..6) {
                    stream.add(Arguments.of(level, list))
                }
            }
            return stream.build()
        }
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    fun testSort(level: Int, testList: MutableList<Int>,) {

        // expected: sort from kotlin.collections
        val expected = testList.toIntArray()
        expected.sort()

        // actual: multi-thread sort
        val unsortedList = testList.toMutableList()
        val actual = unsortedList.sorted(level)

        assertEquals(expected.toList(), actual.toList())
    }
}
