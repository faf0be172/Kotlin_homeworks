package firsthomework

import java.util.Scanner

fun getOccurrencesCount(firstString: String, secondString: String): Int {
    if (firstString.isNotEmpty() && secondString.isNotEmpty() && secondString.length <= firstString.length) {
        return (0..(firstString.length - secondString.length)).count {
            firstString.substring(it, it + secondString.length) == secondString
        }
    }
    return 0
}

fun main() {
    println("Enter two string in different lines to count occurrences:")
    val scanner = Scanner(System.`in`)
    val firstString = scanner.nextLine()
    val secondString = scanner.nextLine()
    println("Number of occurrences: ${getOccurrencesCount(firstString, secondString)}")
}
