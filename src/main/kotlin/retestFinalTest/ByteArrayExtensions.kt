package retestFinalTest

import java.lang.Byte.MAX_VALUE
import java.lang.Byte.MIN_VALUE
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

fun ByteArray.compressed(): ByteArray {
    val byteList = mutableListOf<Byte>()
    val numberList = mutableListOf<Int>()
    var currentByte = this[0]
    var currentNumber = 1

    for (i in 1 until this.size) {
        if (currentByte != this[i]) {
            byteList.add(currentByte)
            numberList.add(currentNumber)
            currentNumber = 1
            currentByte = this[i]
        } else {
            ++currentNumber
        }
    }
    byteList.add(currentByte)
    numberList.add(currentNumber)

    val newByteArray = ByteArray(numberList.size shl 1)
    for (i in byteList.indices) {
        if (numberList[i] >= MAX_VALUE || numberList[i] <= MIN_VALUE) {
            throw IllegalArgumentException("This array contains a very long values in a row")
        }
        newByteArray[2 * i] = numberList[i].toByte()
        newByteArray[2 * i + 1] = byteList[i]
    }
    return newByteArray
}

fun ByteArray.decompressed(): ByteArray {
    if (this.size % 2 == 1) {
        throw IllegalStateException("Number of elements must be even")
    }
    val newByteList = mutableListOf<Byte>()
    for (i in this.indices) {
        if (i % 2 == 0) {
            for (j in 0 until this[i].toInt()) {
                newByteList.add(this[i + 1])
            }
        }
    }
    return newByteList.toByteArray()
}
