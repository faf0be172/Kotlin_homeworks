package homework8

import javafx.scene.control.Label
import tornadofx.App
import tornadofx.View
import tornadofx.label
import tornadofx.vbox
import tornadofx.hbox
import tornadofx.button
import tornadofx.action
import tornadofx.Controller
import tornadofx.launch

const val TABLE_SIZE = 3

class MyApp : App(MyView::class)

class MyView : View("Hello") {
    private val controller: MyController by inject()
    private val field = mutableListOf<MutableList<String>>()
    private fun fillField() {
        repeat(TABLE_SIZE) {
            field.add(mutableListOf("_", "_", "_"))
        }
    }

    override val root = vbox {
        fillField()
        val gameConditionLabel = label("game running")
        for (i in 0 until TABLE_SIZE) {
            hbox {
                for (j in 0 until TABLE_SIZE) {
                    button("_") {
                        action {
                            when (field[i][j]) {
                                "X" -> {
                                    this.text = "0"
                                    field[i][j] = "0"
                                }
                                else -> {
                                    this.text = "X"
                                    field[i][j] = "X"
                                }
                            }
                            if (controller.isGameEnded(field)) {
                                controller.endGame(gameConditionLabel)
                            }
                        }
                    }
                }
            }
        }
    }
}

class MyController : Controller() {
    fun isGameEnded(field: MutableList<MutableList<String>>): Boolean {
        repeat(TABLE_SIZE) {
            if ((field[it][0] == field[it][1] && field[it][0] == field[it][TABLE_SIZE - 1] && field[it][0] != "_")
                || (field[0][it] == field[1][it] && field[0][it] == field[TABLE_SIZE - 1][it] && field[0][it] != "_"))  {
                    return true
            }
        }
        if ((field[0][0] == field[1][1] && field[0][0] == field[TABLE_SIZE - 1][TABLE_SIZE - 1]
                    && field[0][0] != "_")
            || (field[TABLE_SIZE - 1][0] == field[1][1] && field[TABLE_SIZE - 1][0] == field[0][TABLE_SIZE - 1]
                    && field[TABLE_SIZE - 1][0] != "_")) {
            return true
        }
        return false
    }
    fun endGame(gameConditionLabel: Label) {
        gameConditionLabel.text = "game ended"
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}
