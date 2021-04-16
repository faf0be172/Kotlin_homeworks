package homework8

import tornadofx.*

class MyApp : App(MyView::class)

class MyView : View("Hello") {
    override val root = vbox {
        val field = mutableListOf<MutableList<String>>()
        repeat(3) {
            field.add(mutableListOf("Empty", "Empty", "Empty"))
        }
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
                        }
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}