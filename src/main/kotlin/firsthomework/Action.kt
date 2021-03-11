package firsthomework

import kotlinx.serialization.Serializable

@Serializable
sealed class Action {
    abstract fun undo(storage: PerformedCommandStorage)
    abstract fun process(storage: PerformedCommandStorage)
}

@Serializable
class PushFront(private val value: Int) : Action() {
    override fun process(storage: PerformedCommandStorage) {
        storage.arrayDeque.addFirst(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeFirst()
    }
}

@Serializable
class PushBack(private val value: Int) : Action() {
    override fun process(storage: PerformedCommandStorage) {
        storage.arrayDeque.addLast(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeLast()
    }
}

@Serializable
class MoveElement(
    private val indexFrom: Int,
    private val indexTo: Int
) : Action() {
    override fun process(storage: PerformedCommandStorage) {
        storage.arrayDeque.moveElement(indexFrom, indexTo)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.moveElement(indexTo, indexFrom)
    }
}
