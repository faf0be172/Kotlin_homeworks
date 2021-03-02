package firsthomework

import kotlin.math.min
import kotlin.math.max

fun ArrayDeque<Int>.moveElement(indexFrom: Int, indexTo: Int) {

    if (this.size <= max(indexFrom, indexTo) || min(indexFrom, indexTo) < 0) {
        throw ArrayIndexOutOfBoundsException("Some of indexes are out of bounds")
    }
    if (indexFrom != indexTo) {
        val movingElement = this[indexFrom]
        this.removeAt(indexFrom)
        this.add(indexTo, movingElement)
    }
}
