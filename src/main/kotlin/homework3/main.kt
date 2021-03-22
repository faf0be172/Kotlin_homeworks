package homework3

import java.io.File
import java.lang.IllegalArgumentException
import java.util.Scanner

/**
 * [createKtFile] uses [deserializeYamlData] to parse .yaml config
 * and [TestGenerator] to create .kt file
 * [createKtFile] creates .kt file in specified directory
 */

fun transformFile(file: String): String {
    return file
        .replace("  }", "    }")
        .replace("  f", "    f")
        .replace("  @", "    @")
}

fun createKtFile(configPath: String, packagePath: String) {
    val generator = TestGenerator(configPath)
    val file = transformFile(generator.file.toString())
    try {
        File(packagePath).writeText(file)
    } catch (error: IllegalArgumentException) {
        throw IllegalArgumentException("Incorrect kt file path")
    }
}

fun main() {
    println("Enter yaml configuration file path (with .yaml):")
    val configPath = Scanner(System.`in`).next()

    println("Enter test package path (with .kt):")
    val packagePath = Scanner(System.`in`).next()

    createKtFile(configPath, packagePath)
}
