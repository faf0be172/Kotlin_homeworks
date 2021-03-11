package firsthomework

import kotlinx.serialization.Serializable

@Serializable
sealed class Action {
    abstract fun addTo(storage: PerformedCommandStorage)
    abstract fun undo(storage: PerformedCommandStorage)
}

@Serializable
class PushFront(val value: Int) : Action() {
    override fun addTo(storage: PerformedCommandStorage) {
        storage.arrayDeque.addFirst(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeFirst()
    }
}

@Serializable
class PushBack(val value: Int) : Action() {
    override fun addTo(storage: PerformedCommandStorage) {
        storage.arrayDeque.addLast(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeFirst()
    }
}

@Serializable
class MoveElement(
    val indexFrom: Int,
    val indexTo: Int,
) : Action() {
    override fun addTo(storage: PerformedCommandStorage) {
        storage.arrayDeque.moveElement(indexFrom, indexTo)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.moveElement(indexTo, indexFrom)
    }
}
