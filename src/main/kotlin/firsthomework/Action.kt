package firsthomework

abstract class Action {
    abstract fun undo()
}

class PushFront(value: Int, private val storage: PerformedCommandStorage) : Action() {
    init {
        storage.getDeque().addFirst(value)
        storage.getActions().addElement(this)
    }

    override fun undo() {
        storage.getDeque().removeFirst()
        storage.getActions().removeLast()
    }
}

class PushBack(value: Int, private val storage: PerformedCommandStorage) : Action() {
    init {
        storage.getDeque().addLast(value)
        storage.getActions().addElement(this)
    }

    override fun undo() {
        storage.getDeque().removeLast()
        storage.getActions().removeLast()
    }
}

class MoveElement(
    private val indexFrom: Int,
    private val indexTo: Int,
    private val storage: PerformedCommandStorage
) : Action() {
    init {
        storage.getDeque().moveElement(indexFrom, indexTo)
        storage.getActions().addElement(this)
    }

    override fun undo() {
        storage.getDeque().moveElement(indexTo, indexFrom)
        storage.getActions().removeLast()
    }
}
