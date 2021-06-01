package test_2

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
        val listOfNumbers = MutableList(this.size * this.size) { index -> (index / 2) }
        listOfNumbers.shuffle()
        table.clear()
        for (i in 0 until size) {
            table.add(mutableListOf())
            for (j in 0 until size) {
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

    fun makeMove(x: Int, y: Int): List<Pair<Int, Int>> {
        if (table[x][y] in openedNumbers) {
            return emptyList()
        }
        ++parity
        var result = mutableListOf<Pair<Int, Int>>()

        if (parity == 1) {
            firstCell = Pair(x, y)
        }

        if (parity > 1) {
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
        }
        return result
    }

    fun isGameEnded(): Boolean {
        return openedNumbers.size == size * size / 2
    }
}
