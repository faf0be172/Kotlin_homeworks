package homework6

fun MutableList<Int>.sorted(acceptableLevel: Int): MutableList<Int> {
    val sortedList = MutableList(this.size) { 0 }
    this.multiThreadSort(sortedList, Segment(0, this.size - 1), 0, acceptableLevel)
    return sortedList
}

private fun MutableList<Int>.upperBound(value: Int, leftBorder: Int, rightBorder: Int): Int {
    var left = leftBorder
    var right = rightBorder + 1
    while (left < right) {
        val mid = (left + right) shr 1
        if (this[mid] <= value) left = mid + 1
        else right = mid
    }
    return left
}

private data class Segment(val left: Int, val right: Int)

private fun MutableList<Int>.multiThreadMerge(
    resultList: MutableList<Int>,
    leftSegment: Segment,
    rightSegment: Segment,
    begin: Int,
    currentLevel: Int
) {
    val left1 = leftSegment.left
    val right1 = leftSegment.right
    val left2 = rightSegment.left
    val right2 = rightSegment.right
    val leftPartSize = right1 - left1 + 1
    val rightPartSize = right2 - left2 + 1
    if (kotlin.math.max(leftPartSize, rightPartSize) == 0) return

    if (leftPartSize < rightPartSize) {
        this.multiThreadMerge(resultList, Segment(left2, right2), Segment(left1, right1), begin, currentLevel)
        return
    }
    val mid1 = (left1 + right1) / 2
    val mid2 = when {
        left2 > right2 -> left2
        else -> this.upperBound(this[mid1], left2, right2)
    }
    val mid3 = begin + (mid1 - left1) + (mid2 - left2)

    resultList[mid3] = this[mid1]

    if (currentLevel <= 0) {
        this.multiThreadMerge(resultList, Segment(left1, mid1 - 1), Segment(left2, mid2 - 1),
            begin, currentLevel)
        this.multiThreadMerge(resultList, Segment(mid1 + 1, right1), Segment(mid2, right2),
            mid3 + 1, currentLevel)
    } else {

        val leftThread = Thread { this.multiThreadMerge(resultList, Segment(left1, mid1 - 1),
            Segment(left2, mid2 - 1),
            begin, currentLevel - 1) }
        val rightThread = Thread { this.multiThreadMerge(resultList, Segment(mid1 + 1, right1),
            Segment(mid2, right2),
            mid3 + 1, currentLevel - 1) }

        leftThread.start()
        rightThread.start()
        leftThread.join()
        rightThread.join()
    }
}

private fun MutableList<Int>.multiThreadSort(
    resultList: MutableList<Int>,
    segment: Segment,
    begin: Int,
    currentLevel: Int
) {
    val left1 = segment.left
    val right1 = segment.right
    val fragmentSize = right1 - left1 + 1
    when (fragmentSize) {
        0 -> return
        1 -> { resultList[begin] = this[left1] }
        else -> {
            val temporaryArray = MutableList(fragmentSize) { 0 }
            val globalMid = (right1 + left1) shr 1
            val localMid = (right1 - left1) shr 1
            if (currentLevel <= 0) {
                this.multiThreadSort(temporaryArray, Segment(left1, globalMid),
                    0, currentLevel)
                this.multiThreadSort(temporaryArray, Segment(globalMid + 1, right1),
                    localMid + 1, currentLevel)
            } else {

                val leftThread = Thread { this.multiThreadSort(temporaryArray, Segment(left1, globalMid),
                    0, currentLevel - 1) }
                val rightThread = Thread { this.multiThreadSort(temporaryArray, Segment(globalMid + 1, right1),
                    localMid + 1, currentLevel - 1) }

                leftThread.start()
                rightThread.start()
                leftThread.join()
                rightThread.join()
            }
            temporaryArray.multiThreadMerge(resultList,
                Segment(0, localMid),
                Segment(localMid + 1, fragmentSize - 1),
                begin, currentLevel)
        }
    }
}
