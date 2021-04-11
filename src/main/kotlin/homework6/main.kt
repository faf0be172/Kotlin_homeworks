package homework6

fun createRandomMutableList(size: Int): MutableList<Int> {
    val newList = (1..size).toMutableList()
    newList.shuffle()
    return newList
}

fun main() {
    for (size in listOf(50, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000, 2000000, 3000000)) {
        var averageTimeInMs = 0L
        repeat(20) {
            val newList = createRandomMutableList(size)
            val startTime = System.currentTimeMillis()
            newList.multiThreadMergeSort(3)
            val finishTime = System.currentTimeMillis()
            averageTimeInMs += finishTime - startTime
        }
        println("for size = $size average time is ${averageTimeInMs / 20} ms")
    }
}
