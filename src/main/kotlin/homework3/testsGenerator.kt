package homework3

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import java.io.File

@Serializable
data class Function(val name: String)

@Serializable
data class PackageOfTests(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    val functions: List<Function>
)

fun deserializePackageData(fileName: String): PackageOfTests {
    val stringInYamlFormat = File(fileName).inputStream().readAllBytes().toString(Charsets.UTF_8)
    return Yaml.default.decodeFromString(stringInYamlFormat)
}

fun main() {
    val path = "testConfig.yaml"
    val packageOfTests = deserializePackageData(path)
    println("package name: ${packageOfTests.packageName}")
    println("class name: ${packageOfTests.className}")
    for (function in packageOfTests.functions) {
        println("- ${function.name}")
    }
}
