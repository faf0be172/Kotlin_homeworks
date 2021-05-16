package homework8

import homework8.views.StartView
import tornadofx.App
import tornadofx.launch

class TicTacToe : App(StartView::class, GameStyleSheet::class)

fun main() {
    launch<TicTacToe>()
}
