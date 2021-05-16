package homework8

import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.mm
import tornadofx.multi
import tornadofx.c

class GameStyleSheet : Stylesheet() {
    companion object {
        val menuWindow by cssclass()
        private val menuWindowHeight = 50.mm
        private val menuWindowWidth = 140.mm
        private val windowBackgroundColor = c("#DEF7FE")
        private val menuButtonSpacing = 5.mm
        private const val menuFont = "Comic Sans MS"

        val menuButton by cssclass()
        private val menuButtonHeight = 35.mm
        private val menuButtonWidth = 60.mm
        private val menuButtonColor = c("#FFFADD")
        private val menuButtonFontSize = 7.mm

        val menuFragment by cssclass()
        private val menuFragmentHeight = 70.mm
        private val menuFragmentWidth = 110.mm
        private val menuFragmentColor = c("#DEF7FE")

        val menuFragmentButton by cssclass()
        private val menuFragmentButtonColor = c("#FFFADD")
        private val menuFragmentButtonHeight = 20.mm
        private val menuFragmentButtonWidth = 55.mm
        private val menuFragmentButtonFontSize = 7.mm

        val gameWindow by cssclass()
        private val gameWindowHeight = 130.mm
        private val gameWindowWidth = 130.mm

        val fieldButton by cssclass()
        private val fieldButtonHeight = 30.mm
        private val fieldButtonWidth = 30.mm
        private val fieldButtonColor = c("#B5F2EA")
        private val fieldButtonFontSize = 12.mm
        private val fieldButtonFontWeight = FontWeight.MEDIUM
        private const val fieldFont = "Comic Sans MS"

        val resultFragment by cssclass()
        private val resultFragmentHeight = 30.mm
        private val resultFragmentWidth = 70.mm
        private val resultFragmentColor = c("#FDEED9")
        private val resultFragmentFontSize = 7.mm
        private const val resultFont = "Comic Sans MS"
    }

    init {
        menuWindow {
            minHeight = menuWindowHeight
            minWidth = menuWindowWidth
            backgroundColor = multi(windowBackgroundColor)
            spacing = menuButtonSpacing
        }
        menuButton {
            minHeight = menuButtonHeight
            minWidth = menuButtonWidth
            baseColor = menuButtonColor
            fontSize = menuButtonFontSize
            fontFamily = menuFont
        }

        menuFragment {
            prefHeight = menuFragmentHeight
            prefWidth = menuFragmentWidth
            baseColor = menuFragmentColor
            spacing = menuButtonSpacing
            fontSize = menuButtonFontSize
        }
        menuFragmentButton {
            minHeight = menuFragmentButtonHeight
            minWidth = menuFragmentButtonWidth
            baseColor = menuFragmentButtonColor
            fontSize = menuFragmentButtonFontSize
            fontFamily = menuFont
        }

        gameWindow {
            backgroundColor = multi(windowBackgroundColor)
            minHeight = gameWindowHeight
            minWidth = gameWindowWidth
        }

        fieldButton {
            minHeight = fieldButtonHeight
            minWidth = fieldButtonWidth
            baseColor = fieldButtonColor
            fontSize = fieldButtonFontSize
            fontWeight = fieldButtonFontWeight
            fontFamily = fieldFont
        }

        resultFragment {
            minHeight = resultFragmentHeight
            minWidth = resultFragmentWidth
            baseColor = resultFragmentColor
            fontSize = resultFragmentFontSize
            fontFamily = resultFont
        }
    }
}
