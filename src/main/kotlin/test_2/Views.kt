package test_2

import javafx.geometry.Pos
import tornadofx.*

class StartView : View("Find pairs") {

    override val root = vbox {
        this.alignment = Pos.CENTER
        button("Start game") {
            action {
                replaceWith<GameView>(sizeToScene = true)
            }
        }
    }
}

class GameView : View("Find pairs") {
    private val controller: MainController by inject()

    override val root = vbox {
        this.alignment = Pos.CENTER
        for (i in 0 until controller.size) {
            hbox {
                this.alignment = Pos.CENTER
                for (j in 0 until controller.size) {
                    hbox {
                        button {
                            bind(controller.table[i][j])
                            this.isDisable = controller.table[i][j].value != ""
                            action { controller.makeMove(i, j) }
                        }
                    }
                }
            }
        }
    }
}

class EndView : View("Find pairs") {
    override val root = vbox {
        this.alignment = Pos.CENTER
        label("Game over, you won")
        button("Exit") {
            action {
                find<EndView>().close()
            }
        }
    }
}
