package homework3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

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
            ), deserializeYamlData(TestYamlParser::class.java
                .getResource("testConfig1.yaml").path)
            ),
            Arguments.of(TestConfiguration(
                "PcsPackage",
                "PerformedCommandStorage",
                listOf(
                    YamlFunction("serializeActions"),
                    YamlFunction("deserializeActions"),
                    YamlFunction("undoLastAction")
                )
            ), deserializeYamlData(TestYamlParser::class.java
                .getResource("testConfig2.yaml").path)
        )
        )

        @JvmStatic
        fun getIncorrectArguments(): List<Arguments> = listOf(
            Arguments.of(TestYamlParser::class.java
                .getResource("testConfig3.yaml").path),
            Arguments.of(TestYamlParser::class.java
                .getResource("testConfig4.yaml").path),
            Arguments.of("non_existentFile.yaml")
        )
    }

    @MethodSource("getCorrectArguments")
    @ParameterizedTest
    fun testParser(expected: TestConfiguration, actual: TestConfiguration) {
        assertEquals(expected, actual)
    }

    @MethodSource("getIncorrectArguments")
    @ParameterizedTest
    fun testParserExceptions(path: String) {
        assertThrows<IllegalStateException> {
            deserializeYamlData(path)
        }
    }
}
