package firsthomework

interface Action {
    fun undo()
}

class PushFront(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.getDeque.addFirst(value)
        storage.addOperation(this)
    }

    override fun undo() {
        storage.getDeque.removeFirst()
    }
}

class PushBack(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.getDeque.addLast(value)
        storage.addOperation(this)
    }

    override fun undo() {
        storage.getDeque.removeLast()
    }
}

class MoveElement(
    private val indexFrom: Int,
    private val indexTo: Int,
    private val storage: PerformedCommandStorage
) : Action {
    init {
        storage.getDeque.moveElement(indexFrom, indexTo)
        storage.addOperation(this)
    }

    override fun undo() {
        storage.getDeque.moveElement(indexTo, indexFrom)
    }
}
