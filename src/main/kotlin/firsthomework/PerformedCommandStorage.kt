package firsthomework

import java.util.Stack

class PerformedCommandStorage {
    private var actions: Stack<Action> = Stack()
    private var arrayDeque: ArrayDeque<Int> = ArrayDeque()

    fun getDeque() = arrayDeque
    fun getActions() = actions

    fun printCurrentPlacement() {
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun undoLastOperation() {
        if (actions.isEmpty()) println("Empty operation stack, operation failed")
        else actions.lastElement().undo()
    }
}
