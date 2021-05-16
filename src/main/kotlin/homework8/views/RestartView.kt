package homework8.views

import homework8.GameController
import homework8.GameStylesheet
import javafx.geometry.Pos
import tornadofx.button
import tornadofx.View
import tornadofx.hbox
import tornadofx.vbox
import tornadofx.addClass
import tornadofx.action
import tornadofx.Fragment
import tornadofx.text

class FinalView : View("Try again?") {
    private val controller: GameController by inject()
    override val root = vbox {
        hbox {
            addClass(GameStylesheet.menuWindow)
            this.alignment = Pos.CENTER
            button("Restart") {
                addClass(GameStylesheet.menuButton)
                action {
                    controller.backToMenu()
                }
            }

            button("Exit") {
                addClass(GameStylesheet.menuButton)
                action {
                    controller.exit()
                }
            }
        }
    }
}

class ResultFragment(text: String) : Fragment("Game result") {
    override val root = vbox {
        addClass(GameStylesheet.resultFragment)
        this.alignment = Pos.CENTER
        this.text(text)
    }
}
