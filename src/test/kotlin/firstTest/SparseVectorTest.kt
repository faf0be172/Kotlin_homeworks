package firstTest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException


internal class VectorTest {
    @Test
    fun simplePlus() {
        val number1 = ArithmeticInt(1)
        val number2 = ArithmeticInt(2)
        val vector1 = SparseVector(1, mutableMapOf(0 to number1))
        val vector2 = SparseVector(1, mutableMapOf(0 to number2))
        val vector3 = vector1 + vector2
        assertEquals(ArithmeticInt(3), vector3.map[0])
    }

    @Test
    fun simpleMinus() {
        val number1 = ArithmeticInt(10)
        val number2 = ArithmeticInt(22)
        val vector1 = SparseVector(1, mutableMapOf(0 to number1))
        val vector2 = SparseVector(1, mutableMapOf(0 to number2))
        val vector3 = vector1 - vector2
        assertEquals(ArithmeticInt(-12), vector3.map[0])
    }

    @Test
    fun simpleScalarProduct() {
        val number1 = ArithmeticInt(2)
        val number2 = ArithmeticInt(3)
        val vector1 = SparseVector(2, mutableMapOf(0 to number1))
        val vector2 = SparseVector(2, mutableMapOf(0 to number2, 1 to number2))
        val product = vector1 * vector2
        assertEquals(ArithmeticInt(6), product)
    }

    @Test
    fun differentSizesException() {
        val number1 = ArithmeticInt(10)
        val number2 = ArithmeticInt(22)
        val vector1 = SparseVector(1, mutableMapOf(0 to number1))
        val vector2 = SparseVector(2, mutableMapOf(0 to number2))
        assertThrows<IllegalStateException> { vector1 + vector2 }
        assertThrows<IllegalStateException> { vector1 - vector2 }
    }

    @Test
    fun smallSizeException() {
        val number1 = ArithmeticInt(10)
        val number2 = ArithmeticInt(22)
        assertThrows<IllegalArgumentException> {
            SparseVector(1, mutableMapOf(0 to number1, 1 to number2))
        }
    }
}
