package homework8.views

import homework8.GameController
import homework8.GameStyleSheet
import homework8.model.EasyBot
import homework8.model.HardBot
import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import tornadofx.button
import tornadofx.View
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.vbox
import tornadofx.action
import tornadofx.Fragment
import tornadofx.form
import tornadofx.fieldset
import tornadofx.radiobutton

class StartView : View("Tic-Tac-Toe") {
    private val controller: GameController by inject()

    override val root = vbox {
        hbox {
            addClass(GameStyleSheet.menuWindow)
            this.alignment = Pos.CENTER
            button("Play against bot") {
                addClass(GameStyleSheet.menuButton)
                action {
                    controller.gameModel.isBotEnabled = true
                    find<ChooseOptionsFragment>().openModal()
                }
            }

            button("Play with yourself") {
                addClass(GameStyleSheet.menuButton)
                action {
                    controller.gameModel.isBotEnabled = false
                    controller.startGame()
                }
            }
        }
        hbox {
            addClass(GameStyleSheet.menuWindow)
            this.alignment = Pos.CENTER
            button("Exit") {
                addClass(GameStyleSheet.menuButton)
                action {
                    controller.exit()
                }
            }
        }
    }
}

class ChooseOptionsFragment : Fragment("Choose some options") {
    private val fragment = this
    private val controller: GameController by inject()

    private val sideGroup = ToggleGroup()
    private val difficultyGroup = ToggleGroup()

    override val root = form {
        addClass(GameStyleSheet.menuFragment)
        hbox {
            fieldset("Difficulty") {
                hbox {
                    this.alignment = Pos.CENTER
                    addClass(GameStyleSheet.menuFragment)
                    radiobutton("Easy", difficultyGroup) {
                        addClass(GameStyleSheet.menuFragmentButton)
                        action {
                            controller.gameModel.bot = EasyBot(controller)
                        }
                    }
                    radiobutton("Difficult", difficultyGroup) {
                        addClass(GameStyleSheet.menuFragmentButton)
                        action {
                            controller.gameModel.bot = HardBot(controller)
                        }
                    }
                }
            }
        }

        hbox {
            fieldset("Side") {
                hbox {
                    this.alignment = Pos.CENTER
                    addClass(GameStyleSheet.menuFragment)
                    radiobutton("X", sideGroup) {
                        addClass(GameStyleSheet.menuFragmentButton)
                        action {
                            controller.gameModel.botSymbol = "o"
                            controller.gameModel.playerSymbol = "x"
                        }
                    }
                    radiobutton("0", sideGroup) {
                        addClass(GameStyleSheet.menuFragmentButton)
                        action {
                            controller.gameModel.botSymbol = "x"
                            controller.gameModel.playerSymbol = "o"
                        }
                    }
                }
            }
        }

        vbox {
            addClass(GameStyleSheet.menuFragment)
            this.alignment = Pos.CENTER
            hbox {
                this.alignment = Pos.CENTER_RIGHT
                button("Start game") {
                    addClass(GameStyleSheet.menuFragmentButton)
                    action {
                        if (sideGroup.selectedToggle != null && difficultyGroup.selectedToggle != null) {
                            controller.startGame()
                            fragment.close()
                        }
                    }
                }
            }
        }
    }
}
