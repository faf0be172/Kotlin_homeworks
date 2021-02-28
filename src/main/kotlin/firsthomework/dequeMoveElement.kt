package firsthomework

fun ArrayDeque<Int>.moveElement(indexFrom: Int, indexTo: Int) {

    if (this.size <= indexFrom || this.size <= indexTo || indexFrom < 0 || indexTo < 0) {
        throw ArrayIndexOutOfBoundsException("Some of indexes are out of bounds")
    }
    if (indexFrom != indexTo) {
        val movingElement = this[indexFrom]
        this.removeAt(indexFrom)
        this.add(indexTo, movingElement)
    }
}
