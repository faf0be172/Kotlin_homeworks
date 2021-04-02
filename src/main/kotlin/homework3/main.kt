package homework3

import java.util.Scanner

fun main() {
    println("Enter yaml configuration file path (with .yaml):")
    val configPath = Scanner(System.`in`).next()

    println("Enter test package path (with .kt):")
    val packagePath = Scanner(System.`in`).next()

    val generator = TestGenerator(configPath)
    generator.createKtFile(packagePath)
}
