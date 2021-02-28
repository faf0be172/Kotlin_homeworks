package firsthomework

class PerformedCommandStorage {
    private var actions: java.util.Stack<Action> = java.util.Stack()
    private var arrayDeque: ArrayDeque<Int> = ArrayDeque()

    fun getDeque() = arrayDeque
    fun getActions() = actions

    fun printCurrentPlacement() {
        println(this.arrayDeque.joinToString(", "))
    }

    fun undoLastOperation() {
        if (actions.isEmpty()) println("Empty operation stack")
        else actions.lastElement().undo()
    }
}
