package homework4

class AVLTree <Key : Comparable<Key>, Value> : Map <Key, Value> {
    private var root: AVLNode<Key, Value>? = null

    override var size: Int = 0

    override val keys: Set<Key>
        get() = getAVLKeys()

    override val values: Collection<Value>
        get() = getAVLValues()

    override val entries: Set<Map.Entry<Key, Value>>
        get() = getAVLEntries()

    override fun isEmpty(): Boolean = root == null

    fun put(key: Key, value: Value): Value? =
        if (this.root != null) {
            val current = this.root?.recursivePut(key, value)
            if (current == null) this.size++
            this.root = this.root?.balanceSubTree()
            current
        } else {
            this.root = AVLNode(key, value)
            this.size++
            null
        }

    override fun get(key: Key): Value? =
        this.root?.recursiveGet(key)

    override fun containsValue(value: Value): Boolean =
        this.root?.recursiveContainsValue(value) ?: false

    override fun containsKey(key: Key): Boolean =
        this.root?.recursiveContainsKey(key) ?: false

    fun removeKey(key: Key): Value? =
        when {
            this.root != null -> {
                val removedValue = this[key]
                if (removedValue != null) {
                    this.root = this.root?.recursiveRemove(key, null)
                    this.size++
                    removedValue
                } else null
            }
            else -> null
        }

    private fun getAVLKeys(): Set<Key> {
        val keys = mutableSetOf<Key>()
        this.root?.recursiveGetKeys(keys)
        return keys
    }

    private fun getAVLValues(): Set<Value> {
        val values = mutableSetOf<Value>()
        this.root?.recursiveGetValues(values)
        return values
    }

    private fun getAVLEntries(): Set<Map.Entry<Key, Value>> {
        val entries = mutableSetOf<Map.Entry<Key, Value>>()
        return when {
            this.root != null -> {
                this.root!!.recursiveGetEntries(entries)
                entries
            }
            else -> entries
        }
    }

    fun clear() {
        this.root = null
    }
}
