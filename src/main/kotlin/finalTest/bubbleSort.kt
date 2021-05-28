package finalTest

/**
 * [bubbleSorted] uses comparator and sort copy of iterable collection
 * @return [bubbleSorted] sorted iterable collection
 */

fun <T> bubbleSorted(array: Iterable<T>, comparator: Comparator<T>): Iterable<T> {
    var isSwapped = true
    val sortingCollection = array.toMutableList()
    while (isSwapped) {
        isSwapped = false
        for (i in 0..(sortingCollection.size - 2)) {
            if (comparator.compare(sortingCollection[i], sortingCollection[i + 1]) > 0) {
                /**
                 * swap neighboring elements
                 */
                val newLeftValue = sortingCollection[i + 1]
                sortingCollection[i + 1] = sortingCollection[i]
                sortingCollection[i] = newLeftValue
                isSwapped = true
            }
        }
    }
    /**
     * if nothing to swap then return sorted collection
     */
    return sortingCollection
}

fun main() {
    val simpleTestArray =  mutableListOf(1, 5, 6, 4, 10, 7, 2, 10, 0)
    val sortedTestArray = bubbleSorted(simpleTestArray, SimpleComparator())

    println("Unsorted list:")
    println(simpleTestArray.joinToString(separator = ", "))

    println("Sorted list:")
    println(sortedTestArray.joinToString(separator = ", "))
}