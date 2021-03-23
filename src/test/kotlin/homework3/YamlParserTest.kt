package homework3

import com.charleskorn.kaml.MissingRequiredPropertyException
import com.charleskorn.kaml.UnknownPropertyException
import homework3.TestConfiguration.Companion.deserializeYamlData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.FileNotFoundException

@DisplayName("Yaml parser")
internal class TestYamlParser {

    companion object {
       @JvmStatic
        fun getCorrectArguments(): List<Arguments> = listOf(
            Arguments.of(TestConfiguration(
                "PcsPackage",
                "Actions",
                listOf(
                    YamlFunction("pushBack"),
                    YamlFunction("pushFront"),
                    YamlFunction("moveElement")
                )
            ), TestYamlParser::class.java
                .getResource("YamlParserTest/paramTest1/testConfig.yaml").path
            ),
            Arguments.of(TestConfiguration(
                "PcsPackage",
                "PerformedCommandStorage",
                listOf(
                    YamlFunction("serializeActions"),
                    YamlFunction("deserializeActions"),
                    YamlFunction("undoLastAction")
                )
            ), TestYamlParser::class.java
                .getResource("YamlParserTest/paramTest2/testConfig.yaml").path
        )
        )
    }

    @MethodSource("getCorrectArguments")
    @ParameterizedTest
    fun testParser(expected: TestConfiguration, actualPath: String) {
        val actual = deserializeYamlData(actualPath)
        assertEquals(expected, actual)
    }

    @DisplayName("Incorrect properties in .yaml file")
    @Test
    fun testParserExceptionIncorrectProperties() {
        val path = javaClass.getResource(
            "YamlParserTest/incorrectPropertiesTest/testConfig.yaml").path
        assertThrows<UnknownPropertyException> {
            deserializeYamlData(path)
        }
    }

    @DisplayName("Missing properties in .yaml file")
    @Test
    fun testParserExceptionMissingProperties() {
        val path = javaClass.getResource(
            "YamlParserTest/missingPropertiesTest/testConfig.yaml").path
        assertThrows<MissingRequiredPropertyException> {
            deserializeYamlData(path)
        }
    }

    @DisplayName("Missing properties in .yaml file")
    @Test
    fun testParserExceptionConfigNotFound() {
        val path = "nonExistentFile.yaml"
        assertThrows<FileNotFoundException> {
            deserializeYamlData(path)
        }
    }
}
