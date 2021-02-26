package firsthomework

import java.util.Scanner

object FactorialProcessor {

    fun getIterativeFactorial(number: Int): Int {
        var factorial = 1
        for (i in 2..number)
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
    var number = -1
    val scanner = Scanner(System.`in`)
    while (number < 0) {
        println("Enter non-negative whole number to process its factorial:")
        val enteredValue = scanner.next()
        number = try {
            enteredValue.toInt()
        } catch (error: NumberFormatException) {
            println("Entered value is incorrect, try again")
            continue
        }
        if (number < 0) println("Entered number is negative, try again")
    }
    return number
}

fun main() {
    val enteredNumber = enterUserNumber()
    println("Iterative processing result: ${FactorialProcessor.getIterativeFactorial(enteredNumber)}")
    println("Recursive processing result: ${FactorialProcessor.getRecursiveFactorial(enteredNumber)}")
}
