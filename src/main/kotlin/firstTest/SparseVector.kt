package firstTest

import java.lang.IllegalArgumentException

data class SparseVector<T : ArithmeticAvailable<T>>(private val size: Int, val map: MutableMap<Int, T>) {

    init {
        if (size < map.size) {
            throw IllegalArgumentException("Vector size is incorrect")
        }
    }
    private fun checkDimensions(vector: SparseVector<T>): Boolean {
        if (this.size != vector.size) {
            throw IllegalStateException("Vectors' sizes not equal")
        }
        return true
    }

    operator fun plus(vector: SparseVector<T>): SparseVector<T> {
        checkDimensions(vector)

        val keysUnion = vector.map.keys.toMutableSet()
        keysUnion.addAll(this.map.keys)

        val newVectorMap = mutableMapOf<Int, T>()

        for (key in keysUnion.toList()) {
            vector.map[key]?.let {
                newVectorMap.remove(key)
                this.map[key]?.plus(it)?.let { it1 -> newVectorMap.put(key, it1) }
            }
        }
        return SparseVector(size, newVectorMap)
    }

    operator fun minus(vector: SparseVector<T>): SparseVector<T> {
        checkDimensions(vector)

        val keysUnion = vector.map.keys.toMutableSet()
        keysUnion.addAll(this.map.keys)

        val newVectorMap = mutableMapOf<Int, T>()

        for (key in keysUnion.toList()) {
            vector.map[key]?.let {
                newVectorMap.remove(key)
                this.map[key]?.minus(it)?.let { it1 -> newVectorMap.put(key, it1) }
            }
        }
        return SparseVector(size, newVectorMap)
    }

    operator fun times(vector: SparseVector<T>): T {
        checkDimensions(vector)
        if (this.size <= 0 || vector.size <= 0) {
            throw IllegalArgumentException("Any of vectors is null")
        }

        val keysUnion = vector.map.keys.toMutableSet()
        keysUnion.addAll(this.map.keys)

        val componentsResult = mutableListOf<T>()
        for (key in keysUnion.toList()) {
            if (vector.map.containsKey(key)) {
                val currentComponent = vector.map[key]
                if (currentComponent != null) {
                    this.map[key]?.times(currentComponent)?.let { it1 -> componentsResult.add(it1) }
                }
            }
        }
        return componentsResult.fold(componentsResult[0] - componentsResult[0]) { sum, element -> sum + element }
    }
}
