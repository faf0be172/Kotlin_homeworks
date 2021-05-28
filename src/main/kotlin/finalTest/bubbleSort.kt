package finalTest

import java.lang.IllegalArgumentException

/**
 * [bubbleSorted] uses comparator and sort copy of iterable collection
 * @return [bubbleSorted] sorted iterable collection
 */

private fun <T> checkNullsComparator(sortingCollection: MutableList<T>, comparator: Comparator<T>) {
    if (sortingCollection.size > 0) {
        val comparingResult = comparator.compare(sortingCollection[0], sortingCollection[0])
        if (comparingResult != 0) {
            throw IllegalArgumentException("Your comparator return non-null value for similar elements")
        }
    }
}

private fun <T> checkUnequalsElementsComparator(sortingCollection: MutableList<T>, comparator: Comparator<T>) {

    if (sortingCollection.size > 1) {
        val firstComparing = comparator.compare(sortingCollection[0], sortingCollection[1])
        val secondComparing = comparator.compare(sortingCollection[1], sortingCollection[0])
        if (firstComparing > 0 && secondComparing > 0) {
            throw IllegalArgumentException("Your comparator return non-negative values")
        }
        if (firstComparing < 0 && secondComparing < 0) {
            throw IllegalArgumentException("Your comparator return non-positive values")
        }
    }
}

fun <T> bubbleSorted(collection: Iterable<T>, comparator: Comparator<T>): Iterable<T> {
    var isSwapped = true
    val sortingCollection = collection.toMutableList()
    checkNullsComparator(sortingCollection, comparator)
    checkUnequalsElementsComparator(sortingCollection, comparator)

    while (isSwapped) {
        isSwapped = false
        for (i in 0 until sortingCollection.size - 1) {
            val comparatorResult = comparator.compare(
                sortingCollection[i],
                sortingCollection[i + 1]
            )
            if (comparatorResult > 0) {
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
