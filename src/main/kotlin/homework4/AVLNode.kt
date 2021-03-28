package homework4

import kotlin.math.max

abstract class Node <Key : Comparable<Key>, Value>(private val key: Key, open var value: Value) {
    protected var leftChild: AVLNode <Key, Value>? = null
    protected var rightChild: AVLNode <Key, Value>? = null

    abstract fun recursivePut(key: Key, value: Value): Value?
    abstract fun recursiveRemove(key: Key, previousNode: AVLNode<Key, Value>?): AVLNode<Key, Value>?

    fun recursiveGet(key: Key): Value? =
        when {
            key < this.key -> {
                this.leftChild?.recursiveGet(key)
            }
            key > this.key -> {
                this.rightChild?.recursiveGet(key)
            }
            else -> this.value
        }

    fun recursiveContainsValue(value: Value): Boolean =
        when {
            this.value != value -> {
                val containsOnLeft =
                    this.leftChild?.recursiveContainsValue(value) ?: false

                val containsOnRight =
                    this.rightChild?.recursiveContainsValue(value) ?: false

                containsOnLeft || containsOnRight
            }
            else -> true
        }

    fun recursiveContainsKey(key: Key): Boolean =
        when {
        this.key != key -> {
            val containsOnLeft =
                this.leftChild?.recursiveContainsKey(key) ?: false

            val containsOnRight =
                this.rightChild?.recursiveContainsKey(key) ?: false

            containsOnLeft || containsOnRight
        }
        else -> true
    }

    fun recursiveGetKeys(keys: MutableSet<Key>) {
        keys.add(this.key)
        this.leftChild?.recursiveGetKeys(keys)
        this.rightChild?.recursiveGetKeys(keys)
    }

    fun recursiveGetValues(values: MutableSet<Value>) {
        values.add(this.value)
        this.leftChild?.recursiveGetValues(values)
        this.rightChild?.recursiveGetValues(values)
    }

    fun recursiveGetEntries(entries: MutableSet<Map.Entry <Key, Value>>) {
        entries.add(AVLNode(this.key, this.value))
        this.leftChild?.recursiveGetEntries(entries)
        this.rightChild?.recursiveGetEntries(entries)
    }
}

class AVLNode <Key : Comparable<Key>, Value>
    (override val key: Key, override var value: Value) :
    Map.Entry<Key, Value>, Node<Key, Value>(key, value) {

    companion object {
        private const val absoluteCriticalBalanceFactor = 2
    }
    private var height = 0

    private fun fixHeight() {
        val leftSubtreeHeight = this.leftChild?.height ?: 0
        val rightSubtreeHeight = this.rightChild?.height ?: 0
        this.height = max(leftSubtreeHeight, rightSubtreeHeight) + 1
    }

    private fun getBalanceFactor(): Int {
        val leftSubtreeHeight = this.leftChild?.height ?: 0
        val rightSubtreeHeight = this.rightChild?.height ?: 0
        return rightSubtreeHeight - leftSubtreeHeight
    }

    override fun recursivePut(key: Key, value: Value): Value? =
        when {
            key > this.key -> {
                if (this.rightChild == null) {
                    this.rightChild = AVLNode(key, value)
                    null
                } else {
                    this.rightChild?.recursivePut(key, value)
                }
            }
            key < this.key -> {
                if (this.leftChild == null) {
                    this.leftChild = AVLNode(key, value)
                    null
                } else {
                    this.leftChild?.recursivePut(key, value)
                }
            }
            else -> {
                val oldValue = this.value
                this.value = value
                oldValue
            }
        }

    private fun rotateSubTreeRight(): AVLNode <Key, Value>? {
        val current = this.leftChild
        this.leftChild = current?.rightChild
        current?.rightChild = this
        this.fixHeight()
        current?.fixHeight()
        return current
    }

    private fun rotateSubTreeLeft(): AVLNode <Key, Value>? {
        val current = this.rightChild
        this.rightChild = current?.leftChild
        current?.leftChild = this
        this.fixHeight()
        current?.fixHeight()
        return current
    }

    fun balanceSubTree(): AVLNode<Key, Value>? {
        fixHeight()
        return when {
            getBalanceFactor() == absoluteCriticalBalanceFactor -> {
                //  getBalanceFactor() > 0 then this.rightChild.height > 0
                if (this.rightChild?.getBalanceFactor() ?: 1 < 0) {
                    this.rightChild = this.rightChild?.rotateSubTreeRight()
                }
                rotateSubTreeLeft()
            }

            getBalanceFactor() == -absoluteCriticalBalanceFactor -> {
                //  getBalanceFactor() < 0 then this.leftChild.height > 0
                if (this.leftChild?.getBalanceFactor() ?: -1 > 0) {
                    this.leftChild = this.leftChild?.rotateSubTreeLeft()
                }
                rotateSubTreeRight()
            }

            else -> this
        }
    }

    override fun recursiveRemove(key: Key, previousNode: AVLNode<Key, Value>?): AVLNode<Key, Value>? =
        when {
            key < this.key -> {
                this.leftChild = this.leftChild?.recursiveRemove(key, this)
                this
            }
            key > this.key -> {
                this.rightChild = this.rightChild?.recursiveRemove(key, this)
                this
            }
            else -> {
                when {
                    this.leftChild == null -> this.rightChild
                    this.rightChild == null -> this.leftChild
                    else -> {
                        val minElement = this.rightChild?.getSubTreeMin() ?: this
                        if (previousNode != null) {
                            if (previousNode.leftChild == this)
                                previousNode.leftChild = minElement
                            else
                                previousNode.rightChild = minElement
                        }
                        minElement.leftChild = this.leftChild

                        if (this.rightChild?.key != minElement.key) {
                            this.rightChild?.removeMinByKey(key)
                            minElement.rightChild = this.rightChild
                        }
                        else {
                            minElement.rightChild = minElement.rightChild?.rightChild
                        }
                        minElement.balanceSubTree()
                    }
                }
            }
        }

    private fun getSubTreeMin(): AVLNode<Key, Value> =
        this.leftChild?.let { getSubTreeMin() } ?: this

    private fun removeMinByKey(key: Key) {
        if (this.leftChild?.key == key) {
            this.leftChild = null
        } else {
            this.leftChild?.removeMinByKey(key)
        }
    }
}
