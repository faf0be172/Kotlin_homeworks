package homework8

import javafx.scene.control.Label
import tornadofx.*

class MyApp : App(MyView::class)

class MyView : View("Hello") {
    private val controller: MyController by inject()
    private val field = mutableListOf<MutableList<String>>()
    private fun fillField() {
        repeat(3) {
            field.add(mutableListOf("_", "_", "_"))
        }
    }

    override val root = vbox {
        fillField()
        val gameConditionLabel = label ("game running")
        for (i in 0..2) {
            hbox {
                for (j in 0..2) {
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
        repeat(3) {
            if (field[it][0] == field[it][1] && field[it][0] == field[it][2] && field[it][0] != "_") return true
            if (field[0][it] == field[1][it] && field[0][it] == field[2][it] && field[0][it] != "_") return true
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "_") return true
        if (field[2][0] == field[1][1] && field[2][0] == field[0][2] && field[2][0] != "_") return true
        return false
    }
    fun endGame(gameConditionLabel: Label) {
        gameConditionLabel.text = "game ended"
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}