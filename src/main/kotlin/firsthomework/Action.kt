package firsthomework

/**
 * Common interface [Action] for [PushFront], [PushBack] and [MoveElement]
 * [undo] is accessory function for canceling previous operations on [ArrayDeque]
 */

interface Action {
    fun undo()
}

/**
 * Use [PushFront] to insert a new element at the front of the list
 * @param[value] is a new element to push in
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
 */

class PushFront(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.arrayDeque.addFirst(value)
        storage.addAction(this)
    }

    override fun undo() {
        storage.arrayDeque.removeFirst()
    }
}

/**
 * Use [PushBack] to insert a new element at the back of list
 * @param[value] is a new element to push in
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
*/

class PushBack(value: Int, private val storage: PerformedCommandStorage) : Action {
    init {
        storage.arrayDeque.addLast(value)
        storage.addAction(this)
    }

    override fun undo() {
        storage.arrayDeque.removeLast()
    }
}

/**
 * Use [MoveElement] to move any element from one place to another
 * @param[indexFrom] is an index of element to move
 * @param[indexTo] is an index to move to
 * @property[PerformedCommandStorage] is a storage contains the changeable [ArrayDeque]
 */


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
