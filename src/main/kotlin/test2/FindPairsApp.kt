package test2

import tornadofx.App
import tornadofx.find
import tornadofx.launch

class FindPairsApp : App(StartView::class)

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        error("Enter table size")
    }

    val size = args[0].toInt()
    if (size % 2 == 1) {
        error("Incorrect table size")
    }

    val controller = find(MainController::class)
    controller.resetTable(size)
    launch<FindPairsApp>()
}
