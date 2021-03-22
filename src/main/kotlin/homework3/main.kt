package homework3

import java.io.File
import java.lang.IllegalArgumentException
import java.util.Scanner

/**
 * [createKtFile] uses [deserializeYamlData] to parse .yaml config
 * and [TestGenerator] to create file
 * [createKtFile] creates new package with kt. file in specified directory
 */

fun createKtFile(configPath: String, packagePath: String) {
    val generator = TestGenerator(configPath)
    try {
        generator.file.writeTo(File(packagePath))
    } catch (error: IllegalArgumentException) {
        throw IllegalArgumentException("Incorrect kt file path")
    }
}

fun main() {
    println("Enter yaml configuration file path (with .yaml):")
    val configPath = Scanner(System.`in`).next()

    println("Enter test package path:")
    val packagePath = Scanner(System.`in`).next()

    createKtFile(configPath, packagePath)
}
