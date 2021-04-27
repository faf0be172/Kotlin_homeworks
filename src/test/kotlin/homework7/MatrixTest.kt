package homework7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.util.stream.Stream

internal class MatrixTest {
    companion object {
        @JvmStatic
        fun getIncorrectArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(arrayOf(1, 2, 3), arrayOf(1, 2)),
                listOf(arrayOf(1, 2), arrayOf(3, 4))
            ),
            Arguments.of(
                listOf(arrayOf(1, 2), arrayOf(3, 4)),
                listOf(arrayOf(1, 2, 3), arrayOf(4, 5))
            ),
            Arguments.of(
                listOf(arrayOf(1, 2), arrayOf(1, 2)),
                listOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
            )
        )

        @JvmStatic
        fun getCorrectArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Matrix(
                    listOf(
                        arrayOf(5, 1, 2, 6),
                        arrayOf(2, 5, 5, 4),
                        arrayOf(5, 2, 1, 5),
                        arrayOf(7, 9, 4, 9)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(2, 8, 2, 4),
                        arrayOf(6, 9, 8, 3),
                        arrayOf(9, 5, 5, 3),
                        arrayOf(3, 6, 2, 9)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(52, 95, 40, 83),
                        arrayOf(91, 110, 77, 74),
                        arrayOf(46, 93, 41, 74),
                        arrayOf(131, 211, 124, 148)
                    )
                )
            ),

            Arguments.of(
                Matrix(
                    listOf(
                        arrayOf(9, 1, 7, 3, 3),
                        arrayOf(2, 7, 8, 3, 1),
                        arrayOf(5, 1, 5, 2, 1),
                        arrayOf(6, 2, 1, 9, 5),
                        arrayOf(2, 3, 2, 4, 8)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(8, 5, 3, 8, 8),
                        arrayOf(2, 3, 6, 6, 5),
                        arrayOf(3, 2, 5, 8, 1),
                        arrayOf(3, 1, 6, 4, 2),
                        arrayOf(9, 9, 3, 9, 9)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(131, 92, 95, 173, 117),
                        arrayOf(72, 59, 109, 143, 74),
                        arrayOf(72, 49, 61, 103, 63),
                        arrayOf(127, 92, 104, 149, 122),
                        arrayOf(112, 99, 82, 138, 113)
                    )
                )
            ),

            Arguments.of(
                Matrix(
                    listOf(
                        arrayOf(196, 122, 452, 975),
                        arrayOf(512, 471, 728, 339),
                        arrayOf(258, 811, 870, 218),
                        arrayOf(430, 271, 521, 544)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(841, 738, 991, 528),
                        arrayOf(653, 688, 692, 269),
                        arrayOf(489, 160, 757, 250),
                        arrayOf(565, 150, 532, 641)
                    )
                ),
                Matrix(
                    listOf(
                        arrayOf(1016405, 447154, 1139524, 874281),
                        arrayOf(1285682, 869234, 1564768, 796334),
                        arrayOf(1295161, 920272, 1591456, 711621),
                        arrayOf(1100722, 668748, 1297467, 778893)
                    )
                )
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getIncorrectArguments")
    fun testExceptions(rows1: List<Array<Int>>, rows2: List<Array<Int>>) {
        assertThrows<IllegalArgumentException> {
            val matrix1 = Matrix(rows1)
            val matrix2 = Matrix(rows2)
            matrix1.multiplyTo(matrix2)
        }
    }

    @ParameterizedTest
    @MethodSource("getCorrectArguments")
    fun testMultiplying(matrix1: Matrix, matrix2: Matrix, expected: Matrix) {
        assertEquals(matrix1.multiplyTo(matrix2).toString(), expected.toString())
    }
}
