package firstTest

import kotlin.IllegalArgumentException

class ListElement<T>(val value: T) {
    var previous: ListElement<T>? = null
    var next: ListElement<T>? = null
}

class DoublyConnectedList<T> {
    var head: ListElement<T>? = null
    private var tail: ListElement<T>? = null
    var size: Int = 0

    fun isEmpty(): Boolean {
        return head == null
    }

    fun add(element: T) {
        val oldTail: ListElement<T>? = tail
        if(oldTail == null) {
            tail = ListElement(element)
            head = ListElement(element)
            ++size
            return
        }
        val newTail = ListElement(element)
        if (size == 1) {
            head?.next = newTail
            tail = newTail
            tail?.previous = head
            ++size
            return
        }
        oldTail.next = newTail
        newTail.previous = oldTail
        ++size
    }

    fun add(element: T, position: Int) {
        if (position > this.size || position < 0) {
            throw IllegalArgumentException("Incorrect position")
        }
        if (head == null) {
            tail = ListElement(element)
            head = ListElement(element)
            ++size
            return
        }
        if (position == 0) {
            val oldHead = head
            val newHead = ListElement(element)
            newHead.next = oldHead
            oldHead?.previous = newHead
            ++size
            return
        }
        var leftElement = head
        repeat(position - 1) {
            if (leftElement?.next == null) {
                throw IllegalStateException("Incorrect position")
            }
            leftElement = leftElement?.next
        }
        val rightElement = leftElement?.next
        val newElement = ListElement(element)
        leftElement?.next = newElement
        newElement.previous = leftElement
        newElement.next = rightElement
        rightElement?.previous = newElement
        ++size
    }

    fun remove(position: Int) {
        if (isEmpty()) {
            throw IllegalStateException("List is empty")
        }
        if (position < 0 || position >= this.size) {
            throw IllegalArgumentException("Incorrect position")
        }
        if (position == 0) {
            if (head?.next == tail) {
                head = null
                tail = null
                --size
                return
            }
            val newHead = head?.next
            newHead?.previous = null
            head = newHead
            --size
            return
        }
        var leftElement = head
        repeat(position - 1) {
            if (leftElement?.next == null) {
                throw IllegalStateException("Incorrect position")
            }
            leftElement = leftElement?.next
        }
        val removableElement = leftElement?.next ?: throw IllegalStateException("Incorrect position")
        val rightElement = removableElement.next
        leftElement?.next = rightElement
        if (rightElement != null) {
            rightElement.previous = leftElement
        }
        ++size
    }

    fun get(): T {
        if (head == null) {
            throw IllegalStateException("The queue is empty")
        }
        return head!!.value
    }

    fun get(position: Int): T {
        if (isEmpty()) {
            throw IllegalStateException("List is empty")
        }
        if (position < 0 || position >= this.size) {
            throw IllegalArgumentException("Incorrect position")
        }
        var currentElement = head
        repeat(position) {
            if (currentElement?.next == null) {
                throw IllegalArgumentException("Incorrect position")
            }
            currentElement = currentElement?.next
        }
        return currentElement!!.value
    }
}

fun main() {
    val testList = DoublyConnectedList<Int>()
    testList.add(0)
    testList.add(1)
    testList.add(-1, 1)
    println("${testList.get(1)}")
}
