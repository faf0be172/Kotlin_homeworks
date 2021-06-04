package homework8.views

import homework8.GameController
import homework8.GameStyleSheet
import javafx.geometry.Pos
import tornadofx.View
import tornadofx.addClass
import tornadofx.gridpane
import tornadofx.row
import tornadofx.button
import tornadofx.action

class GameView : View("Tic-Tac-Toe") {
    companion object {
        const val SIZE = 3
    }

    private val controller: GameController by inject()

    override val root = gridpane {
        addClass(GameStyleSheet.gameWindow)

        this.alignment = Pos.CENTER
        for (row in 0 until SIZE) {
            row {
                for (column in 0 until SIZE) {
                    button(" ") {
                        addClass(GameStyleSheet.fieldButton)
                        id = "$row$column"
                        action {
                            controller.makeMove(row, column)
                        }
                    }
                }
            }
        }
    }
}
