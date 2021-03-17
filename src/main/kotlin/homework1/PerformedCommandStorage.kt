package homework1

import java.util.Stack

/**
 * [PerformedCommandStorage] contains the list of whole numbers and the queue of actions on it
 * @property[actions] is an ordered private list of actions: [PushFront], [PushBack] and [MoveElement]
 * @property[_arrayDeque] is a private stack of whole numbers, modified by [actions]
 */

class PerformedCommandStorage {
    private val actions: Stack<Action> = Stack()
    private val _arrayDeque: ArrayDeque<Int> = ArrayDeque()

    val arrayDeque: ArrayDeque<Int>
        get() = _arrayDeque

    fun printCurrentPlacement() {
        /**
         * Use the method [printCurrentPlacement] to get current placement of elements of [arrayDeque]
         * @return string with elements of [arrayDeque]
         */
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun addAction(action: Action) = actions.addElement(action)

    fun undoLastAction() {
        /**
         * Use the method [undoLastAction] to cancel previous action on [arrayDeque]
         * This method notice if stack of actions is empty
         */
        if (actions.isNotEmpty()) {
            actions.lastElement().undo()
            actions.removeLast()
        } else println("Empty actions stack, operation failed")
    }
}
