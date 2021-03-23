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
import java.io.File
import java.io.FileNotFoundException
import java.util.stream.Stream
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.createTempDirectory

@DisplayName("Test generator")
internal class TestGeneratorTest {

    companion object {
        @JvmStatic
        fun getDirectories(): Stream<Arguments> = Stream.of(
            Arguments.of(TestGeneratorTest::class.java.getResource(
                "TestGeneratorTest/paramTest1/testConfig.yaml").path.
            removeSuffix("testConfig.yaml")),
            Arguments.of(TestGeneratorTest::class.java.getResource(
                "TestGeneratorTest/paramTest2/testConfig.yaml").path.
            removeSuffix("testConfig.yaml"))
        )
    }

    @MethodSource("getDirectories")
    @ParameterizedTest
    fun testGeneratorByDirectories(directory: String) {
        val generator = TestGenerator("$directory/testConfig.yaml")
        generator.createKtFile("$directory/actual.kt")
        assertEquals(
            File("$directory/actual.kt").readText(),
            File("$directory/expected.kt").readText().replace("\r\n", "\n")
        )
    }

    @DisplayName("Incorrect properties in .yaml file")
    @Test
    fun testGeneratorExceptionIncorrectProperties() {
        val path = javaClass.getResource(
            "YamlParserTest/incorrectPropertiesTest/testConfig.yaml").path
        assertThrows<UnknownPropertyException> {
            deserializeYamlData(path)
        }
    }

    @DisplayName("Missing properties in .yaml file")
    @Test
    fun testGeneratorExceptionMissingProperties() {
        val path = javaClass.getResource(
            "YamlParserTest/missingPropertiesTest/testConfig.yaml").path
        assertThrows<MissingRequiredPropertyException> {
            deserializeYamlData(path)
        }
    }

    @DisplayName("Missing properties in .yaml file")
    @Test
    fun testGeneratorExceptionConfigNotFound() {
        val path = "nonExistentFile.yaml"
        assertThrows<FileNotFoundException> {
            deserializeYamlData(path)
        }
    }
}
