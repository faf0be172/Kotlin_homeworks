package homework6

const val MAX_TEST_ARRAY_SIZE = 2000000
const val TESTS_NUMBER = 10

val TEST_ARRAY_SIZES = getTestArraySizes(
    MAX_TEST_ARRAY_SIZE,
    TESTS_NUMBER)

fun getTestArraySizes(maxTestArraySize: Int, testsNumber: Int): List<Int> {
    val step = maxTestArraySize / testsNumber
    var currentSize = step
    val testArray = mutableListOf<Int>()
    while (currentSize <= MAX_TEST_ARRAY_SIZE) {
        testArray.add(currentSize)
        currentSize += step
    }
    return testArray.toList()
}

fun createRandomMutableList(size: Int): MutableList<Int> {
    val newList = (1..size).toMutableList()
    newList.shuffle()
    return newList
}

fun processSize(size: Int, maxAcceptableLevel: Int, cases: Int): List<Int> {
    val averageTimes = mutableListOf<Int>()
    repeat(maxAcceptableLevel + 1) { averageTimes.add(0) }

    repeat(cases) {
        val newTestingList = createRandomMutableList(size)
        repeat(maxAcceptableLevel + 1) {
            val startTime = System.currentTimeMillis()
            newTestingList.multiThreadMergeSort(acceptableLevel = it)
            val finishTime = System.currentTimeMillis()
            averageTimes[it] = (finishTime - startTime).toInt()
        }
    }
    averageTimes.map { it / cases }
    return averageTimes
}

fun getProcessingTime(maxAcceptableLevel: Int, cases: Int, logs: Boolean): List<Map<Int, Int>> {
    val processingResults: MutableList<MutableMap<Int, Int>> = mutableListOf()
    repeat(maxAcceptableLevel + 1) {
        processingResults.add(mutableMapOf())
    }
    for (i in TEST_ARRAY_SIZES.indices) {
        val size = TEST_ARRAY_SIZES[i]
        val averageTime = processSize(size, maxAcceptableLevel, cases)
        repeat(maxAcceptableLevel + 1) {
            processingResults[it][size] = averageTime[it]
        }
        if (logs) println("processed ${i + 1}/${TEST_ARRAY_SIZES.size}")
    }
    println("processed successfully")
    return processingResults
}
