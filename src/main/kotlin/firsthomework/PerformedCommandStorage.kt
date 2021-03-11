package firsthomework

import java.util.Stack
import java.io.File
import java.io.FileWriter

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PerformedCommandStorage {
    private val actions: Stack<Action> = Stack()
    private val _arrayDeque: ArrayDeque<Int> = ArrayDeque()

    val arrayDeque: ArrayDeque<Int>
        get() = _arrayDeque

    fun addAction(action: Action) {
        actions.addElement(action)
    }

    fun printCurrentPlacement() {
        println("[" + this.arrayDeque.joinToString(separator = ", ") + "]")
    }

    fun serializeActions(fileName: String) {
        val newFile = FileWriter(fileName)
        newFile.write(Json.encodeToString(actions.toList()))
        newFile.flush()
    }

    fun deserializeActions(fileName: String) {
        val stringInJsonFormat = File(fileName).inputStream().readAllBytes().toString(Charsets.UTF_8)
        val listWithActions = Json.decodeFromString<List<Action>>(stringInJsonFormat)

        for (action in listWithActions) {
            action.process(this)
        }
    }

    fun undoLastAction() {
        if (actions.isNotEmpty()) {
            actions.lastElement().undo(this)
            actions.removeLast()
        } else println("Empty actions stack, action failed")
    }
}
