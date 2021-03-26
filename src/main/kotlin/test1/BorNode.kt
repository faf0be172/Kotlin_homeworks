package test1

class BorNode {
    private val edges: MutableMap<Char, BorNode> = mutableMapOf()
    fun recursiveContains(string: String, position: Int): Boolean {
        return when (position) {
            string.length -> true
            else -> {
                if (edges.contains(string[position])) {
                    edges[string[position]]!!.recursiveContains(string, position + 1)
                } else false
            }
        }
    }

    fun recursiveAdd(string: String, position: Int): Boolean {
        return when {
            this.edges.contains(string[position]) -> {
                when (string.length) {
                    position + 1 -> false
                    else -> edges[string[position]]!!.recursiveAdd(string, position + 1)
                }
            }
            else -> {
                this.edges[string[position]] = BorNode()
                when (string.length) {
                    position + 1 -> false
                    else -> this.edges[string[position]]!!.recursiveAdd(string, position + 1)
                }
            }
        }
    }

    fun recursiveRemove(string: String, position: Int): Boolean {
        return when {
            string.isEmpty() -> true
            this.edges.contains(string[position]) -> {
                if (string.length == position + 1) {
                    if (this.edges[string[position]]!!.edges.isEmpty()) {
                        this.edges.remove(string[position])
                        false
                    } else true
                } else {
                    this.edges[string[position]]!!.recursiveRemove(string, position + 1)
                }
            }
            else -> false
        }
    }

    fun recursiveFindByPrefix(prefix: String, position: Int): Int {
        return when (position) {
            prefix.length -> {
                recursiveCountWords()
            }
            else -> {
                if (edges.contains(prefix[position])) {
                    edges[prefix[position]]!!.recursiveFindByPrefix(prefix, position + 1)
                } else 0
            }
        }
    }

    private fun recursiveCountWords(): Int {
        return when {
            (this.edges.isEmpty()) -> 1
            else -> {
                var sum = 0
                for (key in this.edges.keys) {
                    sum += this.edges[key]!!.recursiveCountWords()
                }
                sum
            }
        }
    }
}
