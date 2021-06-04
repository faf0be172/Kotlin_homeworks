package homework8.model

import homework8.GameController

abstract class Bot(val controller: GameController) {
    abstract fun makeMove()
}

class EasyBot(controller: GameController) : Bot(controller) {
    override fun makeMove() {
        val possibleMoves = controller.gameField.getEmptyCells()
        val (i, j) = possibleMoves.random()
        controller.makeMove(i, j)
    }
}

class HardBot(controller: GameController) : Bot(controller) {
    private var huPlayer = " "
    private var aiPlayer = " "

    override fun makeMove() {
        val model = controller.gameModel
        val size = GameController.SIZE

        aiPlayer = model.botSymbol
        huPlayer = model.playerSymbol

        val move = if (controller.gameModel.numberOfMoves == 0) {
            Pair(size / 2, size / 2)
        } else {
            findBestMove()
        }
        controller.makeMove(move.first, move.second)
    }

    private fun findBestMove(): Pair<Int, Int> {
        val emptyCells = controller.gameField.getEmptyCells()
        val newField = controller.gameField.field
        val scores = mutableMapOf<Int, Pair<Int, Int>>()

        for ((i, j) in emptyCells) {
            newField[i][j] = aiPlayer
            scores[minimax(newField, huPlayer)] = Pair(i, j)
            newField[i][j] = " "
        }
        return scores.getOrDefault(scores.keys.maxOrNull()!!, Pair(-1, -1))
    }

    private fun minimax(newField: Array<Array<String>>, player: String): Int {
        val emptyCells = controller.gameField.getEmptyCells()
        val winner = checkField(newField)

        if (winner != "nobody" || emptyCells.isEmpty()) {
            return when (winner) {
                huPlayer -> -1
                aiPlayer -> 1
                else -> 0
            }
        }

        val movesList = mutableListOf<Int>()

        for ((i, j) in emptyCells) {
            newField[i][j] = player
            val opponent = if (player == aiPlayer) huPlayer else aiPlayer
            movesList += minimax(newField, opponent)
            newField[i][j] = " "
        }

        return when (player) {
            // emptyCells[] is not empty than movesList[] is not empty
            aiPlayer -> {
                if (movesList.isEmpty()) 0 else movesList.maxOrNull()!!
            }
            else -> {
                if (movesList.isEmpty()) 0 else movesList.minOrNull()!!
            }
        }
    }
}
