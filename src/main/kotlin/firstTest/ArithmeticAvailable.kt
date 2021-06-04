package firstTest

interface ArithmeticAvailable<T : ArithmeticAvailable<T>> {
    operator fun plus(other: T): T
    operator fun minus(other: T): T
    operator fun times(other: T): T
    fun isZero(): Boolean
}

data class ArithmeticInt(private val actual: Int) : ArithmeticAvailable<ArithmeticInt> {
    override fun plus(other: ArithmeticInt) = ArithmeticInt(actual + other.actual)
    override fun minus(other: ArithmeticInt) = ArithmeticInt(actual - other.actual)
    override fun times(other: ArithmeticInt) = ArithmeticInt(actual * other.actual)
    override fun isZero() = (actual == 0)
}
