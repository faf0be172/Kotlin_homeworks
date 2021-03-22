package homework3

import com.charleskorn.kaml.MissingRequiredPropertyException
import com.charleskorn.kaml.UnknownPropertyException
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
 */

@Serializable
data class TestConfiguration(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    @SerialName("functions")
    val yamlFunctions: List<YamlFunction>
)

/**
 * [safeOpen] is used to safely open .yaml file
 * @return content of .yaml file
 */

fun safeOpen(fileName: String): String {
    return try {
        File(fileName).readText()
    } catch (error: FileNotFoundException) {
        throw error("Yaml config not found")
    }
}

/**
 * [deserializeYamlData] is used to parse .yaml file and get specified configuration
 * @return data class with specified configuration
 */

fun deserializeYamlData(fileName: String): TestConfiguration {
    val stringInYamlFormat = safeOpen(fileName)
    return try {
         Yaml.default.decodeFromString(stringInYamlFormat)
    } catch (error: MissingRequiredPropertyException) {
        throw error("any properties in yaml config are missing")
    } catch (error: UnknownPropertyException) {
        throw error("any properties in yaml config are incorrect")
    }
}
