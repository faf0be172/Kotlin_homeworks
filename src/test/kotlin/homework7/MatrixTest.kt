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
                listOf(intArrayOf(1, 2, 3), intArrayOf(1, 2)),
                listOf(intArrayOf(1, 2), intArrayOf(3, 4))
            ),
            Arguments.of(
                listOf(intArrayOf(1, 2), intArrayOf(3, 4)),
                listOf(intArrayOf(1, 2, 3), intArrayOf(4, 5))
            ),
            Arguments.of(
                listOf(intArrayOf(1, 2), intArrayOf(1, 2)),
                listOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
            )
        )

        @JvmStatic
        fun getCorrectArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Matrix(
                    listOf(
                        intArrayOf(5, 1, 2, 6),
                        intArrayOf(2, 5, 5, 4),
                        intArrayOf(5, 2, 1, 5),
                        intArrayOf(7, 9, 4, 9)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(2, 8, 2, 4),
                        intArrayOf(6, 9, 8, 3),
                        intArrayOf(9, 5, 5, 3),
                        intArrayOf(3, 6, 2, 9)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(52, 95, 40, 83),
                        intArrayOf(91, 110, 77, 74),
                        intArrayOf(46, 93, 41, 74),
                        intArrayOf(131, 211, 124, 148)
                    )
                )
            ),

            Arguments.of(
                Matrix(
                    listOf(
                        intArrayOf(9, 1, 7, 3, 3),
                        intArrayOf(2, 7, 8, 3, 1),
                        intArrayOf(5, 1, 5, 2, 1),
                        intArrayOf(6, 2, 1, 9, 5),
                        intArrayOf(2, 3, 2, 4, 8)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(8, 5, 3, 8, 8),
                        intArrayOf(2, 3, 6, 6, 5),
                        intArrayOf(3, 2, 5, 8, 1),
                        intArrayOf(3, 1, 6, 4, 2),
                        intArrayOf(9, 9, 3, 9, 9)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(131, 92, 95, 173, 117),
                        intArrayOf(72, 59, 109, 143, 74),
                        intArrayOf(72, 49, 61, 103, 63),
                        intArrayOf(127, 92, 104, 149, 122),
                        intArrayOf(112, 99, 82, 138, 113)
                    )
                )
            ),

            Arguments.of(
                Matrix(
                    listOf(
                        intArrayOf(196, 122, 452, 975),
                        intArrayOf(512, 471, 728, 339),
                        intArrayOf(258, 811, 870, 218),
                        intArrayOf(430, 271, 521, 544)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(841, 738, 991, 528),
                        intArrayOf(653, 688, 692, 269),
                        intArrayOf(489, 160, 757, 250),
                        intArrayOf(565, 150, 532, 641)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(1016405, 447154, 1139524, 874281),
                        intArrayOf(1285682, 869234, 1564768, 796334),
                        intArrayOf(1295161, 920272, 1591456, 711621),
                        intArrayOf(1100722, 668748, 1297467, 778893)
                    )
                )
            ),
            Arguments.of(
                Matrix(
                    listOf(
                        intArrayOf(4, -1, -6),
                        intArrayOf(9, 6, 4),
                        intArrayOf(-3, -2, 0)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(-5, -100, -5),
                        intArrayOf(1, 6, 0),
                        intArrayOf(32, 9, -10)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(-213, -460, 40),
                        intArrayOf(89, -828, -85),
                        intArrayOf(13, 288, 15)
                    )
                )
            ),
            Arguments.of(
                Matrix(
                    listOf(
                        intArrayOf(0, 0, 0),
                        intArrayOf(-6, 5, -1),
                        intArrayOf(-5, -4, 6)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(-5, -100, -5),
                        intArrayOf(1, 6, 0),
                        intArrayOf(32, 9, -10)
                    )
                ),
                Matrix(
                    listOf(
                        intArrayOf(0, 0, 0),
                        intArrayOf(3, 621, 40),
                        intArrayOf(213, 530, -35)
                    )
                )
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getIncorrectArguments")
    fun testExceptions(rows1: List<IntArray>, rows2: List<IntArray>) {
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
