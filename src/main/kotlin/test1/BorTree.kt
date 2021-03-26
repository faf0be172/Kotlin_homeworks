package test1

class BorTree {
    private val root = BorNode()
    var size = 0
    fun contains(string: String): Boolean {
        return this.root.recursiveContains(string, position = 0)
    }

    fun add(string: String) {
        if (this.root.recursiveAdd(string, position = 0)) this.size++
    }

    fun remove(string: String): Boolean {
        val isStringIn = this.contains(string)
        if (isStringIn) this.size--
        this.root.recursiveRemove(string, position = 0)
        return isStringIn
    }

    fun howManyStartWithPrefix(prefix: String): Int {
        return this.root.recursiveFindByPrefix(prefix, position = 0)
    }

    fun size(): Int {
        return this.size
    }
}
