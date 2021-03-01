package firsthomework

import java.util.Stack

class PerformedCommandStorage {
    private val actions: Stack<Action> = Stack()
    private val arrayDeque: ArrayDeque<Int> = ArrayDeque()

    val getDeque: ArrayDeque<Int>
        get() = this.arrayDeque

    fun printCurrentPlacement() {
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun addOperation(operation: Action) {
        actions.addElement(operation)
    }

    fun undoLastOperation() {
        if (actions.isNotEmpty()) {
            actions.lastElement().undo()
            actions.removeLast()
        } else println("Empty operation stack, operation failed")
    }
}
