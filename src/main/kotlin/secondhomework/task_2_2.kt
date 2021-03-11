package secondhomework

import kotlin.random.Random

fun createRandomArray(arraySize: Int, rangeFrom: Int, rangeTo: Int): IntArray {
    return IntArray(arraySize) { Random.nextInt(from = rangeFrom, until = rangeTo) }
}

fun IntArray.removeDuplicateElements(): IntArray {
    val temporarySet: LinkedHashSet<Int> = LinkedHashSet()
    temporarySet.addAll(this.toMutableList().reversed())
    return temporarySet.reversed().toIntArray()
}

fun main() {
    val randomArray = createRandomArray(arraySize = 13, rangeFrom = 1, rangeTo = 6)
    println("Array: " + randomArray.joinToString(separator = ", "))
    val processedArray = randomArray.removeDuplicateElements()
    println("Processed array: " + processedArray.joinToString(separator = ", "))
}
