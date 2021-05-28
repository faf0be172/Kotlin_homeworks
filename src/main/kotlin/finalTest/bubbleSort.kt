package finalTest

fun <T> bubbleSorted(array: Array<T>, comparator: Comparator<T>): Array<T> {
    var isSwapped = true
    val sortingArray = array.clone()
    while (isSwapped) {
        isSwapped = false
        for (i in 0..(sortingArray.size - 2)) {
            if (comparator.compare(sortingArray[i], sortingArray[i + 1]) > 0) {
                val newLeftValue = sortingArray[i + 1]
                sortingArray[i + 1] = sortingArray[i]
                sortingArray[i] = newLeftValue
                isSwapped = true
            }
        }
    }
    return sortingArray
}

fun main() {
    val simpleTestArray = arrayOf(1, 5, 6, 4, 10, 7, 2, 10, 0)
    val sortedTestArray = bubbleSorted(simpleTestArray, SimpleComparator())

    println("Unsorted list:")
    println(simpleTestArray.joinToString(separator = ", "))

    println("Sorted list:")
    println(sortedTestArray.joinToString(separator = ", "))
}