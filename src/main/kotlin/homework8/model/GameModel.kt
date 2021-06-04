package homework8.model

import homework8.GameController

class GameModel(private val controller: GameController) {

    private var isFinished = false
    private val maxPossibleMoves = GameController.SIZE * GameController.SIZE

    var playerSymbol = "x"
    var botSymbol = "o"
    var numberOfMoves = 0
    var isBotEnabled = false
    var bot: Bot = EasyBot(controller)

    fun tryToMoveBot() {
        if (isBotEnabled && !isFinished) {
            if (numberOfMoves % 2 == 0 && botSymbol == "x") {
                bot.makeMove()
            }
            if (numberOfMoves % 2 == 1 && botSymbol == "o") {
                bot.makeMove()
            }
        }
    }

    fun makeMove(row: Int, column: Int) {
        val side = if (numberOfMoves % 2 == 0) "x" else "o"
        controller.gameField.makeMove(row, column, side)
        ++numberOfMoves

        val winner = checkField(controller.gameField.field)

        if (winner != "nobody" || numberOfMoves == maxPossibleMoves) {
            controller.finishGame(winner)
            isFinished = true
        }
    }

    fun clearGame() {
        controller.gameField.clear()
        numberOfMoves = 0
        isFinished = false
    }
}
