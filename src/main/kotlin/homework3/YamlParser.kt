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

@Serializable
data class TestConfiguration(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    @SerialName("functions")
    val yamlFunctions: List<YamlFunction>
)

fun deserializeYamlData(fileName: String): TestConfiguration {
    val stringInYamlFormat = try {
        File(fileName).readText()
    } catch (error: FileNotFoundException) {
        throw error("Yaml config not found")
    }
    try {
        return Yaml.default.decodeFromString(stringInYamlFormat)
    } catch (error: MissingRequiredPropertyException) {
        throw error("any properties in yaml config are missing")
    } catch (error: UnknownPropertyException) {
        throw error("any properties in yaml config are incorrect")
    }
}
