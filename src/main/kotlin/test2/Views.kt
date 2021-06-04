package test2

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.View
import tornadofx.vbox
import tornadofx.style
import tornadofx.button
import tornadofx.action
import tornadofx.hbox
import tornadofx.paddingTop
import tornadofx.paddingLeft
import tornadofx.bind
import tornadofx.px
import tornadofx.label

private const val SIZE_OF_BUTTON = 35.0
private const val SIZE_OF_FONT = 15.0
private const val HEIGHT_OF_WINDOW = 100.0
private const val WIDTH_OF_WINDOW = 200.0
private const val PADDING_OF_OBJECTS = 3

class StartView : View("Find pairs") {

    override val root = vbox {
        this.alignment = Pos.CENTER
        prefHeight = HEIGHT_OF_WINDOW
        prefWidth = WIDTH_OF_WINDOW
        style {
            backgroundColor += Color.ALICEBLUE
        }
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
        minHeight = HEIGHT_OF_WINDOW + HEIGHT_OF_WINDOW
        minWidth = WIDTH_OF_WINDOW + HEIGHT_OF_WINDOW
        style {
            backgroundColor += Color.ALICEBLUE
        }
        for (i in 0 until controller.size) {
            hbox {
                this.alignment = Pos.CENTER
                paddingTop = PADDING_OF_OBJECTS
                for (j in 0 until controller.size) {
                    hbox {
                        paddingLeft = PADDING_OF_OBJECTS
                        button {
                            bind(controller.table[i][j])
                            this.isDisable = controller.table[i][j].value != ""
                            action { controller.makeMove(i, j) }

                            style {
                                fontSize = SIZE_OF_FONT.px
                                backgroundColor += Color.LIGHTGREEN
                                fontWeight = FontWeight.BOLD
                            }
                            maxHeight = SIZE_OF_BUTTON
                            maxWidth = SIZE_OF_BUTTON
                            minHeight = SIZE_OF_BUTTON
                            minWidth = SIZE_OF_BUTTON
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
        prefHeight = HEIGHT_OF_WINDOW
        prefWidth = WIDTH_OF_WINDOW
        style {
            backgroundColor += Color.ALICEBLUE
        }
        label("Game over, you won")
        button("Exit") {
            action {
                find<EndView>().close()
            }
        }
    }
}
