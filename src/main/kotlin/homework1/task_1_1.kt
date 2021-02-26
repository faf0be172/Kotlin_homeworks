package homework1

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

fun main() {
    println("Enter number to process its factorial:")

    val scan = Scanner(System.`in`)
    val enteredNumber = scan.nextInt()

    val factorialProcessor = FactorialProcessor()
    println("Iterative processing result: ${factorialProcessor.getIterativeFactorial(enteredNumber)}")
    println("Recursive processing result: ${factorialProcessor.getRecursiveFactorial(enteredNumber)}")
}
