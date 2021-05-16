package homework8.model

import homework8.GameController

fun checkField(field: Array<Array<String>>): String {
    var result = "nobody"
    val size = field.size
    val xWin = "x".repeat(size)
    val oWin = "o".repeat(size)

    for (row in field) {
        if (row.joinToString("") == xWin) result = "x"
        if (row.joinToString("") == oWin) result = "o"
    }

    for (column in 0 until size) {
        val string = buildString {
            for (row in 0 until size) {
                append(field[row][column])
            }
        }
        if (string == xWin) result = "x"
        if (string == oWin) result = "o"
    }

    val string1 = buildString {
        for (i in 0 until size) {
            append(field[i][i])
        }
    }
    if (string1 == xWin) result = "x"
    if (string1 == oWin) result = "o"

    val string2 = buildString {
        for (i in 0 until size) {
            append(field[i][size - i - 1])
        }
    }
    if (string2 == xWin) result = "x"
    if (string2 == oWin) result = "o"

    return result
}

class GameField {
    private val size = GameController.SIZE
    val field = Array(size) { Array(size) { " " } }

    fun getEmptyCells(): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (this.field[i][j] == " ") {
                    list.add(Pair(i, j))
                }
            }
        }
        return list
    }

    fun makeMove(row: Int, column: Int, side: String) {
        field[row][column] = side
    }

    fun clear() {
        for (i in 0 until size) {
            for (j in 0 until size) { field[i][j] = " " }
        }
    }
}
