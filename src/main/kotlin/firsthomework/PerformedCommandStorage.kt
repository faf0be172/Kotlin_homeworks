package firsthomework

import java.util.Stack
import java.io.File
import java.io.FileWriter

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * [PerformedCommandStorage] contains the list of whole numbers and the queue of actions on it
 * @property[actions] is an ordered private list of actions: [PushFront], [PushBack] and [MoveElement]
 * @property[localArrayDeque] is a private stack of whole numbers, modified by [actions]
 */

class PerformedCommandStorage {
    private val actions: Stack<Action> = Stack()
    private val localArrayDeque: ArrayDeque<Int> = ArrayDeque()

    val arrayDeque: ArrayDeque<Int>
        get() = localArrayDeque

    fun printCurrentPlacement() {
        /**
         * Use the method [printCurrentPlacement] to get current placement of elements of [arrayDeque]
         * @return string with elements of [arrayDeque]
         */
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun addAction(action: Action) = actions.addElement(action)

    fun serializeActions(fileName: String) {
        val newFile = FileWriter(fileName)
        newFile.write(Json.encodeToString(actions.toList()))
        newFile.flush()
    }

    fun deserializeActions(fileName: String) {
        val stringInJsonFormat = File(fileName).readText()
        val listWithActions = Json.decodeFromString<List<Action>>(stringInJsonFormat)

        for (action in listWithActions) {
            action.process(this)
        }
    }

    fun undoLastAction() {
        /**
         * Use the method [undoLastAction] to cancel previous action on [arrayDeque]
         * This method notice if stack of actions is empty
         */
        if (actions.isNotEmpty()) {
            actions.lastElement().undo(this)
            actions.removeLast()
        } else println("Empty actions stack, operation failed")
    }
}
