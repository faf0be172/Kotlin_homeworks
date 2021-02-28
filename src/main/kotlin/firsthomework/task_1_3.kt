package firsthomework

fun testStorage() {
    val testingStorage = PerformedCommandStorage()
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    PushBack(value = 1, testingStorage)
    testingStorage.printCurrentPlacement()
    PushFront(value = -1, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    testingStorage.printCurrentPlacement()
    PushBack(value = 2, testingStorage)
    PushBack(value = 3, testingStorage)
    PushBack(value = 4, testingStorage)
    MoveElement(indexFrom = 0, indexTo = 2, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    testingStorage.printCurrentPlacement()
}

fun main() {
    testStorage()
}
