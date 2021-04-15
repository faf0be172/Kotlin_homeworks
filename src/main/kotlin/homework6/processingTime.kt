package homework6

import kotlin.math.max
import kotlin.math.pow

val TEST_ARRAY_SIZES = arrayListOf(5000, 10000, 50000, 100000, 500000, 700000, 1000000, 1200000, 1500000, 1700000, 2000000)

fun createRandomMutableList(size: Int): MutableList<Int> {
    val newList = (1..size).toMutableList()
    newList.shuffle()
    return newList
}

fun processSize(size: Int, maxAcceptableLevel: Int, cases: Int): List<Int> {
    val averageTimes = mutableListOf<Int>()
    repeat(maxAcceptableLevel + 1) { averageTimes.add(0)}

    repeat(cases) {
        val newTestingList = createRandomMutableList(size)
        repeat(maxAcceptableLevel + 1) {
            val startTime = System.currentTimeMillis()
            newTestingList.multiThreadMergeSort(acceptableLevel = it);
            val finishTime = System.currentTimeMillis()
            averageTimes[it] = (finishTime - startTime).toInt()
        }
    }
    averageTimes.map { it/cases }
    return averageTimes
}

fun getProcessingTime(maxAcceptableLevel: Int, cases: Int, logs: Boolean): List<Map<Int, Int>> {
    val processingResults: MutableList<MutableMap<Int, Int>> = mutableListOf()
    repeat(maxAcceptableLevel + 1) {
        processingResults.add(mutableMapOf())
    }
    for (i in 0 until TEST_ARRAY_SIZES.size) {
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
