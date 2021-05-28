package finalTest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.util.stream.Stream
import kotlin.random.Random

internal class BubbleSortKtTest {
    companion object {
        private fun getRandomIntArray() = MutableList(20) { Random.nextInt(1, 20) }

        @JvmStatic
        fun getArguments(): Stream<Arguments> {
            val randomLists = List(5) { getRandomIntArray() }
            val stream = Stream.builder<Arguments>()
            for (list in randomLists) {
                stream.add(Arguments.of(list))
            }
            return stream.build()
        }
    }

    @Test
    fun emptyArray() {
        assertEquals(
            mutableListOf<Int>().toList(),
            bubbleSorted(mutableListOf(), naturalOrder<Int>()).toList()
        )
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    fun randomIntArrays(list: MutableList<Int>) {
        assertEquals(
            list.sorted(),
            bubbleSorted(list, naturalOrder())
        )
    }

    @Test
    fun sortAlreadySortedIntArray() {
        assertEquals(
            mutableListOf(1, 2, 3, 4, 10),
            bubbleSorted(mutableListOf(1, 2, 3, 4, 10), naturalOrder())
        )
    }

    @Test
    fun sortWithBadComparatorNotSimilarElements() {
        assertThrows<IllegalArgumentException> {
            bubbleSorted(mutableListOf(1, 2, 3, 4, 10), BadComparator1())
        }
    }

    @Test
    fun sortWithBadComparatorSimilarElements() {
        assertThrows<IllegalArgumentException> {
            bubbleSorted(mutableListOf(1, 2, 3, 4, 10), BadComparator2())
        }
    }

    @Test
    fun sortWithDividingByZero() {
        assertThrows<IllegalStateException> {
            bubbleSorted(mutableListOf(1, 2, 3, 3, 10), DividingByZeroComparator())
        }
    }
}
