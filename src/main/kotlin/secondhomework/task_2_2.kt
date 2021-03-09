package secondhomework

import kotlin.random.Random

fun createRandomArray(arraySize: Int): IntArray {
    return IntArray(arraySize) { Random.nextInt(from = 1, until = 6) }
}

fun IntArray.removeDuplicateElements(): IntArray {
    val temporarySet: LinkedHashSet<Int> = LinkedHashSet()
    temporarySet.addAll(this.toMutableList().reversed())
    return temporarySet.reversed().toIntArray()
}

fun main() {
    val randomArray = createRandomArray(arraySize = 13)
    println("Array: " + randomArray.joinToString(separator = ", "))
    val processedArray = randomArray.removeDuplicateElements()
    println("Processed array: " + processedArray.joinToString(separator = ", "))
}
