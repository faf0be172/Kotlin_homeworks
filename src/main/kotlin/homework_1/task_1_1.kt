package homework_1

import java.util.Scanner

fun getIterativeFactorial(number: Int): Int {
    var factorial = 1
    for (i in 2 until number + 1)
        factorial *= i
    return factorial
}

fun getRecursiveFactorial(number: Int): Int {
    return recursiveFactorial(1, number, 1)
}

fun recursiveFactorial(count: Int, factorialDegree: Int, result: Int): Int {
    return if (count <= factorialDegree)
        recursiveFactorial(count + 1, factorialDegree, result * count)
    else result
}

fun enterUserNumber(): Int {
    println("Enter number to process its factorial:")
    val getNumber = Scanner(System.`in`)
    return getNumber.nextInt()
}

fun main() {
    val enteredNumber = enterUserNumber()
    println("Iterative processing result: ${getIterativeFactorial(enteredNumber)}")
    println("Recursive processing result: ${getRecursiveFactorial(enteredNumber)}")
}
