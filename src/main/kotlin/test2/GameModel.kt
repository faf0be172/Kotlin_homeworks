package test2

class GameModel(tableSize: Int) {
    private var table = mutableListOf<MutableList<Int>>()
    private var firstCell = Pair(-1, -1)
    private var secondCell = Pair(-1, -1)
    private var openedNumbers = mutableListOf<Int>()

    private var parity = 0
    private val size = tableSize

    init {
        generateTable()
    }

    private fun generateTable() {
        val listOfNumbers = MutableList(this.size * this.size) { index -> (index shr 1) }
        listOfNumbers.shuffle()
        table.clear()
        for (i in 0 until size) {
            table.add(mutableListOf())
            repeat(size) {
                table[i].add(listOfNumbers.first())
                listOfNumbers.removeAt(0)
            }
        }
    }

    private fun checkNumbers() {
        if (table[firstCell.first][firstCell.second] == table[secondCell.first][secondCell.second]) {
            openedNumbers.add(table[firstCell.first][firstCell.second])
        }
    }

    private fun getDoubleMoveResult(x: Int, y: Int): List<Pair<Int, Int>> {
        val result: MutableList<Pair<Int, Int>>
        secondCell = Pair(x, y)
        if (firstCell == secondCell) {
            firstCell = Pair(-1, -1)
            secondCell = Pair(-1, -1)
            parity = 0
            return emptyList()
        }

        checkNumbers()
        val firstValue = table[firstCell.first][firstCell.second]
        val secondValue = table[secondCell.first][secondCell.second]
        result = mutableListOf(firstCell, secondCell, Pair(firstValue, secondValue))

        firstCell = Pair(-1, -1)
        secondCell = Pair(-1, -1)
        parity = 0
        return result
    }

    fun makeMove(x: Int, y: Int): List<Pair<Int, Int>> {
        if (table[x][y] in openedNumbers) {
            return emptyList()
        }
        ++parity
        return when (parity) {
            1 -> {
                firstCell = Pair(x, y)
                emptyList()
            }
            else -> getDoubleMoveResult(x, y)
        }
    }

    fun isGameEnded(): Boolean {
        return openedNumbers.size == size * size shr 1
    }
}
