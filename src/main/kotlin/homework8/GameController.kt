package homework8

import homework8.model.GameField
import homework8.model.GameModel
import homework8.views.FinalView
import homework8.views.GameView
import homework8.views.ResultFragment
import homework8.views.StartView
import tornadofx.Controller
import tornadofx.text

class GameController : Controller() {
    companion object {
        const val SIZE = 3
    }
    val gameModel = GameModel(this)
    val gameField = GameField()

    fun startGame() {
        gameModel.tryToMoveBot()
        find<StartView>().replaceWith<GameView>(sizeToScene = true)
    }

    fun makeMove(row: Int, column: Int) {
        gameModel.makeMove(row, column)
        val button = find<GameView>().root.lookup("#$row$column")
        button.isDisable = true
        if (gameModel.numberOfMoves % 2 == 1) {
            button.text("X")
        } else {
            button.text("0")
        }
        gameModel.tryToMoveBot()
    }

    fun backToMenu() {
        gameModel.clearGame()
        clearButtons()
        find<FinalView>().replaceWith<StartView>(sizeToScene = true)
    }

    fun exit() {
        find<StartView>().close()
        find<FinalView>().close()
    }

    fun finishGame(winner: String) {
        find<GameView>().replaceWith<FinalView>(sizeToScene = true)
        val resultFragmentString = if (winner == "nobody") "Draw" else "\"${winner.toUpperCase()}\" player won"
        val resultFragment = ResultFragment(resultFragmentString)
        resultFragment.openModal()
    }

    private fun clearButtons() {
        val gameView = find<GameView>()
        for (row in 0 until SIZE) {
            for (column in 0 until SIZE) {
                val button = gameView.root.lookup("#$row$column")
                button.text(" ")
                button.isDisable = false
            }
        }
    }
}
