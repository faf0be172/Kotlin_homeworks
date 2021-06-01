package homework3

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ClassName
import homework3.TestConfiguration.Companion.deserializeYamlData
import java.io.File
import java.lang.IllegalArgumentException

/**
 * [TestGenerator] uses [deserializeYamlData] and create .kt file
 * @property[_testsConfiguration] is used to store specified config
 * @property[file] contains [FileSpec] object ready to write to the .kt file
 * @return [createFunction] kotlinpoet function to insert to the test class
 * @return [createTestClass] kotlinpoet class to insert to the .kt file
 * [createKtFile] uses [deserializeYamlData] and [file] to parse .yaml config to create .kt file
 */

class TestGenerator(configPath: String) {

    private val _testsConfiguration = deserializeYamlData(configPath)
    private val packageName = _testsConfiguration.packageName
    private val className = _testsConfiguration.className
    private val functions = _testsConfiguration.yamlFunctions

    private val file: FileSpec
        get() = FileSpec.builder(this.packageName, "${this.className}Test")
            .addType(createTestClass())
            .build()

    private fun createFunction(function: YamlFunction) = FunSpec.builder(function.name)
        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
        .build()

    private fun createTestClass(): TypeSpec {
        val testClass = TypeSpec.classBuilder("${this.className}Test")
            .addModifiers(KModifier.INTERNAL)
        for (function in this.functions)
            testClass.addFunction(createFunction(function))
        return testClass.build()
    }

    private fun transformFile(file: String): String {
        return file
            .replace("  }", "    }")
            .replace("  f", "    f")
            .replace("  @", "    @")
    }

    /**
     * @param [packagePath] is a path of new .kt file
     */

    fun createKtFile(packagePath: String) {
        val file = transformFile(this.file.toString())
        try {
            File(packagePath).writeText(file)
        } catch (error: IllegalArgumentException) {
            throw IllegalArgumentException("Incorrect kt file path $error")
        }
    }
}
