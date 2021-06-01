package test_2

import tornadofx.App
import tornadofx.find
import tornadofx.launch

class FindPairsApp : App(StartView::class)

fun main() {
    val size = 4
    if (size % 2 == 1) {
        error("Incorrect table size")
    }

    val controller = find(MainController::class)
    controller.resetTable(size)
    launch<FindPairsApp>()
}
