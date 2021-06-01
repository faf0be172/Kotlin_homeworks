package test2

import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller

class MainController : Controller() {
    var size = 0
    private var model = GameModel(size)
    var table = Array(size) { Array(size) { SimpleStringProperty("") } }

    private var isValuesEqual = true
    private var firstCell = Pair(-1, -1)
    private var secondCell = Pair(-1, -1)

    fun resetTable(size: Int) {
        this.size = size
        model = GameModel(size)
        table = Array(size) { Array(size) { SimpleStringProperty("") } }
    }

    fun makeMove(x: Int, y: Int) {
        if (!isValuesEqual) {
            table[firstCell.first][firstCell.second].value = ""
            table[secondCell.first][secondCell.second].value = ""
            isValuesEqual = true
        }

        val result = model.makeMove(x, y)
        if (result.isEmpty()) {
            return
        }

        firstCell = result[0]
        secondCell = result[1]
        val firstValue = result[2].first.toString()
        val secondValue = result[2].second.toString()
        table[firstCell.first][firstCell.second].value = firstValue
        table[secondCell.first][secondCell.second].value = secondValue

        if (firstValue != secondValue) {
            isValuesEqual = false
        }

        if (model.isGameEnded()) {
            find(GameView::class).replaceWith<EndView>(sizeToScene = true)
        }
    }
}
