package firsthomework

import java.util.Scanner

fun getOccurrencesCount(firstString: String, secondString: String): Int {
    if (firstString.isNotEmpty() && secondString.isNotEmpty() && secondString.length <= firstString.length) {
        var mainString = firstString
        var count = 0
        while (mainString.substringAfter(secondString, "").isNotEmpty()) {
            mainString = (secondString + mainString.substringAfter(secondString, "")).drop(1)
            count++;
        }
        if (mainString == secondString) count++
        return count
    }
    return 0
}

fun main() {
    println("Enter two string in different lines to count occurrences:")
    val scanner = Scanner(System.`in`)
    val firstString = scanner.nextLine()
    val secondString = scanner.nextLine()
    println("${getOccurrencesCount(firstString, secondString)}")
}