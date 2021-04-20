package homework6

import java.util.Stack

fun MutableList<Int>.multiThreadMergeSort(acceptableLevel: Int) {
    this.mergeMultiThreadSort(0, this.size - 1, 0, acceptableLevel)
}

private fun MutableList<Int>.mergeSingleThreadSort(first: Int, last: Int) {
    if (first < last) {
        val medium = (first + last) shr 1
        this.mergeSingleThreadSort(first, medium)
        this.mergeSingleThreadSort(medium + 1, last)
        this.mergeParts(first, last)
    }
}

private fun MutableList<Int>.mergeMultiThreadSort(first: Int, last: Int, currentLevel: Int, acceptableLevel: Int) {
    if (first < last) {
        val medium = (first + last) shr 1
        val leftRunnable = when {
            currentLevel < acceptableLevel -> Runnable {
                this.mergeMultiThreadSort(
                    first,
                    medium,
                    currentLevel + 1,
                    acceptableLevel)
            }
            else -> Runnable { this.mergeSingleThreadSort(first, medium) }
        }
        val rightRunnable = when {
            currentLevel < acceptableLevel -> Runnable {
                this.mergeMultiThreadSort(
                    medium + 1,
                    last,
                    currentLevel + 1,
                    acceptableLevel)
            }
            else -> Runnable { this.mergeSingleThreadSort(medium + 1, last) }
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
    val medium = (first + last) shr 1
    var leftPartCurrent = first
    var rightPartCurrent = medium + 1
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
        if (leftPartCurrent == medium + 1) leftPartCurrent = -1
        if (rightPartCurrent == last + 1) rightPartCurrent = -1
    }

    repeat(last - first + 1) {
        this[first + it] = mergedList[it]
    }
}
