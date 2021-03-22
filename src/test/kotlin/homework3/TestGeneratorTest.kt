package homework3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.createTempDirectory

@DisplayName("Test generator")
internal class TestGeneratorTest {

    companion object {
        @JvmStatic
        fun getCorrectArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                TestGeneratorTest::class.java
                    .getResource("ActionsTest.kt").path,
                TestGeneratorTest::class.java
                    .getResource("testConfig1.yaml").path,
                "ActionsTest"
            ),
            Arguments.of(
                TestGeneratorTest::class.java
                    .getResource("PerformedCommandStorageTest.kt").path,
                TestGeneratorTest::class.java
                    .getResource("testConfig2.yaml").path,
                "PerformedCommandStorageTest"
            )
        )

        @JvmStatic
        fun getIncorrectArguments(): List<Arguments> = listOf(
            Arguments.of(TestYamlParser::class.java
                .getResource("testConfig3.yaml").path),
            Arguments.of(TestYamlParser::class.java
                .getResource("testConfig4.yaml").path)
        )
    }

    @ExperimentalPathApi
    @MethodSource("getCorrectArguments")
    @ParameterizedTest
    fun testGenerator(expectedFilePath: String, configPath: String, actualFileName: String) {
        val tempDirectory = createTempDirectory("generatorTests")
        createKtFile(configPath, "$tempDirectory/$actualFileName.kt")
        assertEquals(
            File(expectedFilePath).readText().replace("\r\n", "\n"),
            File(tempDirectory.toAbsolutePath().toString() +
                    "/$actualFileName.kt").readText())
    }

    @MethodSource("getIncorrectArguments")
    @ParameterizedTest
    fun testGeneratorExceptions(configPath: String) {
        assertThrows<IllegalStateException> {
            createKtFile(configPath, "temp.kt")
        }
    }
}
