package homework6

import kotlin.random.Random

class SortingProcessor {
    companion object {
        private const val MAX_TEST_ARRAY_SIZE = 2000000
        private const val TESTS_NUMBER = 10
    }

    private val testArraySizes: List<Int> = getTestArraySizes()

    private fun getTestArraySizes(): List<Int> {
        val step = MAX_TEST_ARRAY_SIZE / TESTS_NUMBER
        var currentSize = step
        val testArray = mutableListOf<Int>()
        while (currentSize <= MAX_TEST_ARRAY_SIZE) {
            testArray.add(currentSize)
            currentSize += step
        }
        return testArray.toList()
    }

    private fun processSize(size: Int, maxAcceptableLevel: Int, cases: Int): List<Int> {
        val averageTimes = MutableList(maxAcceptableLevel + 1) { 0 }

        repeat(cases) {
            val newTemplate = MutableList(size) { Random.nextInt(0, Int.MAX_VALUE) }

            for(level in 0..maxAcceptableLevel) {
                val temporaryList = newTemplate.toMutableList()

                val startTime = System.currentTimeMillis()
                temporaryList.multiThreadMergeSort(level)

                val finishTime = System.currentTimeMillis()
                averageTimes[level] = (finishTime - startTime).toInt()
            }
        }
        averageTimes.map { it / cases }
        return averageTimes
    }

    fun getProcessingTime(maxAcceptableLevel: Int, cases: Int, logs: Boolean): List<Map<Int, Int>> {
        val processingResults = MutableList(maxAcceptableLevel + 1) { mutableMapOf<Int, Int>()}

        for ((index, value) in testArraySizes.withIndex()) {
            val averageTime = processSize(value, maxAcceptableLevel, cases)
            for (level in 0..maxAcceptableLevel) {
                processingResults[level][value] = averageTime[level]
            }
            if (logs) println("processed ${index + 1}/${testArraySizes.size}")
        }

        println("processed successfully")
        return processingResults
    }
}
