package homework8.views

import homework8.GameController
import homework8.GameStylesheet
import javafx.geometry.Pos
import tornadofx.*

class GameView : View("Tic-Tac-Toe") {
    companion object {
        const val SIZE = 3
    }

    private val controller: GameController by inject()

    override val root = gridpane {
        addClass(GameStylesheet.gameWindow)

        this.alignment = Pos.CENTER
        for (row in 0 until SIZE) {
            row {
                for (column in 0 until SIZE) {
                    button(" ") {
                        addClass(GameStylesheet.fieldButton)
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
