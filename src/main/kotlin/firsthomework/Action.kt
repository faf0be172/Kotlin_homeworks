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
        TODO("Not yet implemented")
    }
}

class PushBack(value: Int, private val storage: PerformedCommandStorage) : Action() {
    init {
        storage.getDeque().addLast(value)
        storage.getActions().addElement(this)
    }

    override fun undo() {
        TODO("Not yet implemented")
    }
}

class MoveElement(value: Int, private val storage: PerformedCommandStorage) : Action() {
    init {
        TODO("ArrayDeque.moveElement() not yet implemented")
        storage.getActions().addElement(this)
    }

    override fun undo() {
        TODO("Not yet implemented")
    }
}
