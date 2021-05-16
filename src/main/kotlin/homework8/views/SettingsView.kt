package homework8.views

import homework8.GameController
import homework8.GameStylesheet
import homework8.model.EasyBot
import homework8.model.HardBot
import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import tornadofx.*

class StartView : View("Tic-Tac-Toe") {
    private val controller: GameController by inject()

    override val root = vbox {
        hbox {
            addClass(GameStylesheet.menuWindow)
            this.alignment = Pos.CENTER
            button("Play against bot") {
                addClass(GameStylesheet.menuButton)
                action {
                    controller.gameModel.isBotEnabled = true
                    find<ChooseOptionsFragment>().openModal()
                }
            }

            button("Play with yourself") {
                addClass(GameStylesheet.menuButton)
                action {
                    controller.gameModel.isBotEnabled = false
                    controller.startGame()
                }
            }
        }
        hbox {
            addClass(GameStylesheet.menuWindow)
            this.alignment = Pos.CENTER
            button("Exit") {
                addClass(GameStylesheet.menuButton)
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
        addClass(GameStylesheet.menuFragment)
        hbox {
            fieldset("Difficulty") {
                hbox {
                    this.alignment = Pos.CENTER
                    addClass(GameStylesheet.menuFragment)
                    radiobutton("Easy", difficultyGroup) {
                        addClass(GameStylesheet.menuFragmentButton)
                        action {
                            controller.gameModel.bot = EasyBot(controller)
                        }
                    }
                    radiobutton("Difficult", difficultyGroup) {
                        addClass(GameStylesheet.menuFragmentButton)
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
                    addClass(GameStylesheet.menuFragment)
                    radiobutton("X", sideGroup) {
                        addClass(GameStylesheet.menuFragmentButton)
                        action {
                            controller.gameModel.botSymbol = "o"
                            controller.gameModel.playerSymbol = "x"
                        }
                    }
                    radiobutton("0", sideGroup) {
                        addClass(GameStylesheet.menuFragmentButton)
                        action {
                            controller.gameModel.botSymbol = "x"
                            controller.gameModel.playerSymbol = "o"
                        }
                    }
                }
            }
        }

        vbox {
            addClass(GameStylesheet.menuFragment)
            this.alignment = Pos.CENTER
            hbox {
                this.alignment = Pos.CENTER_RIGHT
                button("Start game") {
                    addClass(GameStylesheet.menuFragmentButton)
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
