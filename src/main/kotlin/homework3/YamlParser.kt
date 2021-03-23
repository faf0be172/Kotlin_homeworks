package homework3

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import java.io.File
import java.io.FileNotFoundException

@Serializable
data class YamlFunction(val name: String)

/**
 * [TestConfiguration] is a auxiliary data class and used to deserialize .yaml files with specified configuration
 * @param[yamlFunctions] contains the list of auxiliary [YamlFunction] data classes
 * [deserializeYamlData] is used to parse .yaml file and get specified configuration
 */

@Serializable
data class TestConfiguration(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    @SerialName("functions")
    val yamlFunctions: List<YamlFunction>
) {
    companion object {
        fun deserializeYamlData(fileName: String): TestConfiguration {
            if (File(fileName).exists()) {
                val stringInYamlFormat = File(fileName).readText()
                return Yaml.default.decodeFromString(stringInYamlFormat)
            } else {
                throw FileNotFoundException(".yaml file not found")
            }
        }
    }
}
