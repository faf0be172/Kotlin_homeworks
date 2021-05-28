package finalTest

class SimpleComparator : Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        return when {
            o1 > o2 -> -1
            o1 < o2 -> 1
            else -> 0
        }
    }
}

class BadComparator1 : Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        return when {
            o1 > o2 -> 1
            o1 < o2 -> 1
            else -> 0
        }
    }
}

class BadComparator2 : Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        return when {
            o1 > o2 -> -1
            o1 < o2 -> 1
            else -> 1
        }
    }
}

class DividingByZeroComparator: Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        val difference = o1 / (o1 - o2)
        return when {
            difference > 0 -> 1
            difference < 0 -> -1
            else -> 0
        }
    }
}
