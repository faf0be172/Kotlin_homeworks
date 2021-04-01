package homework3

import com.charleskorn.kaml.MissingRequiredPropertyException
import com.charleskorn.kaml.UnknownPropertyException
import homework3.TestConfiguration.Companion.deserializeYamlData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.io.FileNotFoundException
import java.util.stream.Stream

@DisplayName("Test generator")
internal class TestGeneratorTest {

    companion object {
        @JvmStatic
        fun getDirectories(): Stream<Arguments> = Stream.of(
            Arguments.of("paramTest1"),
            Arguments.of("paramTest2")
        )
    }

    @MethodSource("getDirectories")
    @ParameterizedTest
    fun testGeneratorByDirectories(testName: String, @TempDir tempKtFile: File) {
        val configPath = javaClass.getResource("TestGeneratorTest/$testName/testConfig.yaml").path
        val expectedKtFilePath = javaClass.getResource("TestGeneratorTest/$testName/expected.kt").path
        val generator = TestGenerator(configPath)
        val tempFile = tempKtFile.resolve("actual.kt")
        generator.createKtFile(tempFile.path)
        assertEquals(
            File(tempFile.path).readText(),
            File(expectedKtFilePath).readText().replace("\r\n", "\n")
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
