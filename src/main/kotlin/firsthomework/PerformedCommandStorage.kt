package firsthomework

import java.util.Stack

class PerformedCommandStorage {
    private val actions: Stack<Action> = Stack()
    private val _arrayDeque: ArrayDeque<Int> = ArrayDeque()

    val arrayDeque: ArrayDeque<Int>
        get() = _arrayDeque

    fun printCurrentPlacement() {
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun addAction(action: Action) {
        actions.addElement(action)
    }

    fun undoLastAction() {
        if (actions.isNotEmpty()) {
            actions.lastElement().undo()
            actions.removeLast()
        } else println("Empty actions stack, action failed")
    }
}
