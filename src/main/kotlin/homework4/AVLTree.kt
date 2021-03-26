package homework4

class AVLTree <Key : Comparable<Key>, Value> {
    var root: AVLNode<Key, Value>? = null

    fun put(key: Key, value: Value): Value? {
        return if (this.root != null) {
            val current = this.root!!.recursivePut(key, value)
            this.root = this.root!!.balanceSubTree()
            current
        } else {
            this.root = AVLNode(key, value)
            null
        }
    }

    fun get(key: Key): Value? {
        return if (this.root != null) {
            this.root!!.recursiveGet(key)
        } else {
            null
        }
    }

    fun containsValue(value: Value): Boolean {
        return if (this.root != null) {
            this.root!!.recursiveContainsValue(value)
        } else {
            false
        }
    }

    fun containsKey(key: Key): Boolean {
        return if (this.root != null) {
            this.root!!.recursiveContainsKey(key)
        } else {
            false
        }
    }

    fun removeKey(key: Key): Value? {
        return when {
            this.root != null -> {
                val removedValue = this.get(key)
                if (removedValue != null) {
                    this.root = this.root!!.recursiveRemove(key)
                    removedValue
                } else null
            }
            else -> null
        }
    }

    fun getKeys(): List<Key> {
        val list = mutableListOf<Key>()
        return when {
            this.root != null -> {
                this.root!!.recursiveGetKeys(list)
                list
            }
            else -> list
        }
    }

    fun getValues(): List<Value> {
        val list = mutableListOf<Value>()
        return when {
            this.root != null -> {
                this.root!!.recursiveGetValues(list)
                list
            }
            else -> list
        }
    }

    fun getEntries(): List<Pair<Key, Value>> {
        val list = mutableListOf<Pair<Key, Value>>()
        return when {
            this.root != null -> {
                this.root!!.recursiveGetEntries(list)
                list
            }
            else -> list
        }
    }

    fun putAllLacking(tree: AVLTree<Key, Value>) {
        for (entries in tree.getEntries()) {
            if (!this.containsKey(entries.first)) {
                this.put(entries.first, entries.second)
            }
        }
    }
}
