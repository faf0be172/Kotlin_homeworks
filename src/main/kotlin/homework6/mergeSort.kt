package homework6

import java.util.*

fun MutableList<Int>.multiThreadMergeSort(acceptableLevel: Int) {
    this.mergeMultiThreadSort(0, this.size - 1, 0, acceptableLevel)
}

fun MutableList<Int>.mergeSort() {
    this.mergeSort(0, this.size - 1)
}

private fun MutableList<Int>.mergeSort(first: Int, last: Int) {
    if (first < last) {
        this.mergeSort(first, (first + last) / 2)
        this.mergeSort((first + last) / 2 + 1, last)
        this.mergeParts(first, last)
    }
}

private fun MutableList<Int>.mergeMultiThreadSort(first: Int, last: Int, currentLevel: Int, acceptableLevel: Int) {
    if (first < last) {
        val leftRunnable = when {
            currentLevel < acceptableLevel -> Runnable { this.mergeMultiThreadSort(first, (first + last) / 2, currentLevel + 1, acceptableLevel) }
            else -> Runnable { this.mergeSort(first, (first + last) / 2) }
        }
        val rightRunnable = when {
            currentLevel < acceptableLevel -> Runnable { this.mergeMultiThreadSort((first + last) / 2 + 1, last, currentLevel + 1, acceptableLevel) }
            else -> Runnable { this.mergeSort((first + last) / 2 + 1, last) }
        }

        val leftThread = Thread(leftRunnable)
        val rightThread = Thread(rightRunnable)
        leftThread.start()
        rightThread.start()
        leftThread.join()
        rightThread.join()
        this.mergeParts(first, last)
    }
}

private fun MutableList<Int>.mergeParts(first: Int, last: Int) {
    var leftPartCurrent = first
    var rightPartCurrent = (first + last) / 2 + 1
    val mergedList = Stack<Int>()
    repeat(last - first + 1) {
        when {
            leftPartCurrent < 0 -> {
                mergedList.push(this[rightPartCurrent])
                ++rightPartCurrent
            }
            rightPartCurrent < 0 -> {
                mergedList.push(this[leftPartCurrent])
                ++leftPartCurrent
            }
            else -> {
                if (this[leftPartCurrent] < this[rightPartCurrent]) {
                    mergedList.push(this[leftPartCurrent])
                    ++leftPartCurrent
                } else {
                    mergedList.push(this[rightPartCurrent])
                    ++rightPartCurrent
                }
            }
        }
        if (leftPartCurrent == (first + last) / 2 + 1) leftPartCurrent = -1
        if (rightPartCurrent == last + 1) rightPartCurrent = -1
    }
    repeat(last - first + 1) {
        this[first + it] = mergedList[it]
    }
}