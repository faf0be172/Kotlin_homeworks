package firsthomework

fun testStorage() {
    val testingStorage = PerformedCommandStorage()
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    PushBack(value = 1).process(testingStorage)
    testingStorage.printCurrentPlacement()
    PushFront(value = -1).process(testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    testingStorage.printCurrentPlacement()
    PushBack(value = 2).process(testingStorage)
    PushBack(value = 3).process(testingStorage)
    PushBack(value = 4).process(testingStorage)
    MoveElement(indexFrom = 0, indexTo = 2).process(testingStorage)
    testingStorage.printCurrentPlacement()
    testingStorage.undoLastAction()
    testingStorage.printCurrentPlacement()
    testingStorage.serializeActions(fileName = "storageData.json")

    val loadedTestingStorage = PerformedCommandStorage()
    loadedTestingStorage.deserializeActions(fileName = "storageData.json")
    println("Loaded storage:")
    loadedTestingStorage.printCurrentPlacement()
    loadedTestingStorage.undoLastAction()
    loadedTestingStorage.printCurrentPlacement()
    loadedTestingStorage.undoLastAction()
    loadedTestingStorage.printCurrentPlacement()
    PushBack(value = 5).process(loadedTestingStorage)
    loadedTestingStorage.printCurrentPlacement()
}

fun main() {
    testStorage()
}
