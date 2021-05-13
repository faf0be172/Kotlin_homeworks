package homework7

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Matrix(private val rows: List<IntArray>) {
    init {
        rows.forEach {
            require(it.size == rows.size) { "Matrix is not square" }
        }
    }

    private val size = rows.size
    private val columns: List<IntArray> = MutableList(this.size) {
        IntArray(this.size) { 0 }
    }.mapIndexed { index, _ -> (rows.map { it[index] }).toIntArray() }

    override fun toString() = rows.joinToString(separator = "\n", transform = { it.joinToString(separator = "\t") })

    fun multiplyTo(matrix: Matrix): Matrix {
        require(this.size == matrix.size) { "Dimensions are not equal" }
        val resultMatrixRows = List(this.size) { IntArray(this.size) { 0 } }

        val leftRows = this.rows
        val rightColumns = matrix.columns
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
            jobList.forEach { it.join() }
        }
        return Matrix(resultMatrixRows)
    }

    private fun multiplyRowAndColumn(row: IntArray, column: IntArray): Int {
        require(row.size == column.size) { "Dimensions are not equal" }
        return row.zip(column).map { it.first * it.second }.sum()
    }
}
