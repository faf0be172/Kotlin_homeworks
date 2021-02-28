package firsthomework

fun main() {
    val testingStorage = PerformedCommandStorage()
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    PushBack(1, testingStorage)
    testingStorage.printCurrentPlacement()
    PushFront(-1, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    testingStorage.printCurrentPlacement()
    PushBack(2, testingStorage)
    PushBack(3, testingStorage)
    PushBack(4, testingStorage)
    MoveElement(0, 2, testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastOperation()
    testingStorage.printCurrentPlacement()
}
