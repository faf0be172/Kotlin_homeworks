package homework1

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Common sealed class [Action] for [PushFront], [PushBack] and [MoveElement]
 * [undo] is accessory function for canceling previous operations on [ArrayDeque]
 */

@Serializable
sealed class Action {
    abstract fun undo(storage: PerformedCommandStorage)
    abstract fun process(storage: PerformedCommandStorage)
}

/**
 * Use [PushFront] to insert a new element at the front of the list
 * @param[value] is a new element to push in
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
 */

@Serializable
@SerialName("PushFront")
class PushFront(private val value: Int) : Action() {
    override fun process(storage: PerformedCommandStorage) {
        storage.arrayDeque.addFirst(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeFirst()
    }
}

/**
 * Use [PushBack] to insert a new element at the back of list
 * @param[value] is a new element to push in
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
*/

@Serializable
@SerialName("PushBack")
class PushBack(private val value: Int) : Action() {
    override fun process(storage: PerformedCommandStorage) {
        storage.arrayDeque.addLast(value)
        storage.addAction(this)
    }

    override fun undo(storage: PerformedCommandStorage) {
        storage.arrayDeque.removeLast()
    }
}

/**
 * Use [MoveElement] to move any element from one place to another
 * @param[indexFrom] is an index of element to move
 * @param[indexTo] is an index to move to
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
 *
 * Method [moveElement] removes element from [indexFrom], moves left certain elements
 * and inserts removed element on [indexTo]
 */

@Serializable
@SerialName("MoveElement")
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
