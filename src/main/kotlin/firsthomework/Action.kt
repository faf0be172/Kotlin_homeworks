package firsthomework

interface Action {
    fun undo()
}

class PushFront(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.arrayDeque.addFirst(value)
        storage.addAction(this)
    }

    override fun undo() {
        storage.arrayDeque.removeFirst()
    }
}

class PushBack(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.arrayDeque.addLast(value)
        storage.addAction(this)
    }

    override fun undo() {
        storage.arrayDeque.removeLast()
    }
}

class MoveElement(
    private val indexFrom: Int,
    private val indexTo: Int,
    private val storage: PerformedCommandStorage
) : Action {
    init {
        storage.arrayDeque.moveElement(indexFrom, indexTo)
        storage.addAction(this)
    }

    override fun undo() {
        storage.arrayDeque.moveElement(indexTo, indexFrom)
    }
}
