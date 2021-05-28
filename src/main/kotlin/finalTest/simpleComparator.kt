package finalTest

class SimpleComparator: Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        return when {
            o1 > o2 -> -1
            o1 < o2 -> 1
            else -> 0
        }
    }
}