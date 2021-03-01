package firsthomework

import java.util.Scanner

fun String.getOccurrencesCount(pattern: String): Int {
    if (this.isNotEmpty() && pattern.isNotEmpty() && pattern.length <= this.length) {
        return this.windowed(pattern.length).filter { pattern == it }.size
    }
    return 0
}

fun main() {
    println("Enter two string in different lines to count occurrences:")
    val scanner = Scanner(System.`in`)
    val originalString = scanner.nextLine()
    val pattern = scanner.nextLine()
    println("Number of occurrences: ${originalString.getOccurrencesCount(pattern)}")
}
