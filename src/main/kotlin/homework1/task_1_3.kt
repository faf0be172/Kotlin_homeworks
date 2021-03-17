package homework1

fun testStorage() {
    val testingStorage = PerformedCommandStorage()
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    PushBack(value = 1, testingStorage)
    testingStorage.printCurrentPlacement()
    PushFront(value = -1, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    testingStorage.printCurrentPlacement()
    PushBack(value = 2, testingStorage)
    PushBack(value = 3, testingStorage)
    PushBack(value = 4, testingStorage)
    MoveElement(indexFrom = 0, indexTo = 2, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    testingStorage.printCurrentPlacement()
}

fun main() {
    testStorage()
}
