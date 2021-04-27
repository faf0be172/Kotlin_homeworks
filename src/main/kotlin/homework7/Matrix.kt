package homework7

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.StringBuilder

class Matrix(private val rows: List<IntArray>) {
    companion object {
        const val COROUTINES_LAUNCH_INDICATOR = 100
    }
    init {
        for (row in rows) {
            require(row.size == rows.size) { "Matrix is not square" }
        }
    }

    private val size = rows.size
    private val columns: List<IntArray> = run {
        val columns = List(this.size) { IntArray(this.size) { 0 } }
        for ((index1, column) in columns.withIndex()) {
            for ((index2, row) in this.rows.withIndex()) {
                column[index2] = (row[index1])
            }
        }
        columns
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (row in this.rows) {
            stringBuilder.append(row.joinToString(separator = "\t", postfix = "\n"))
        }
        return stringBuilder.toString()
    }

    fun multiplyTo(matrix: Matrix): Matrix {
        require(this.size == matrix.size) { "Dimensions are not equal" }
        val resultMatrixRows = List(this.size) { IntArray(this.size) { 0 } }

        val leftRows = this.rows
        val rightColumns = matrix.columns
        if (matrix.size >= COROUTINES_LAUNCH_INDICATOR) {
            val jobList = mutableListOf<Job>()
            runBlocking {
                for ((index1, row) in leftRows.withIndex()) {
                    // possibly faster than N * N coroutines
                    jobList.add(launch {
                        for ((index2, column) in rightColumns.withIndex()) {
                            resultMatrixRows[index1][index2] = multiplyRowAndColumn(row, column)
                        }
                    })
                }
                for (job in jobList) { job.join() }
            }
        } else {
            for ((index1, row) in leftRows.withIndex()) {
                for ((index2, column) in rightColumns.withIndex()) {
                    resultMatrixRows[index1][index2] = multiplyRowAndColumn(row, column)
                }
            }
        }
        return Matrix(resultMatrixRows)
    }

    private fun multiplyRowAndColumn(row: IntArray, column: IntArray): Int {
        require(row.size == column.size) { "Dimensions are not equal" }
        var resultSum = 0
        for (index in row.indices) {
            resultSum += row[index] * column[index]
        }
        return resultSum
    }
}
