package firsthomework

import java.util.Scanner

private class FactorialProcessor {

    fun getIterativeFactorial(number: Int): Int {
        var factorial = 1
        for (i in 2 until number + 1)
            factorial *= i
        return factorial
    }

    fun getRecursiveFactorial(factorialDegree: Int, count: Int = 1, result: Int = 1): Int {
        return if (count <= factorialDegree) {
            getRecursiveFactorial(factorialDegree, count + 1, result * count)
        } else result
    }
}

fun enterUserNumber(): Int {
    var number = 0
    while (number <= 0) {
        println("Enter positive whole number to process its factorial:")
        val enteredValue = Scanner(System.`in`).next()
        number = try {
            enteredValue.toInt()
        } catch (error: NumberFormatException) {
            println("Entered value is incorrect, try again")
            continue
        }
        if (number <= 0) println("Entered number is non-positive, try again")
    }
    return number
}

fun main() {
    val enteredNumber = enterUserNumber()
    val factorialProcessor = FactorialProcessor()
    println("Iterative processing result: ${factorialProcessor.getIterativeFactorial(enteredNumber)}")
    println("Recursive processing result: ${factorialProcessor.getRecursiveFactorial(enteredNumber)}")
}
