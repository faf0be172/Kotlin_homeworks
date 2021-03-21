package homework3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

@DisplayName("Test generator")
internal class TestGeneratorTest() {
    companion object {
        @JvmStatic
        fun getCorrectArguments(): List<Arguments> = listOf(
            Arguments.of(
                TestGeneratorTest::class.java
                    .getResource("PcsPackage/ActionsTest.kt").path,
                TestGeneratorTest::class.java
                    .getResource("testConfig2.yaml").path,
                "ActionsTest"
            ),
            Arguments.of(
                TestGeneratorTest::class.java
                    .getResource("PcsPackage/PerformedCommandStorageTest.kt").path,
                TestGeneratorTest::class.java
                    .getResource("testConfig1.yaml").path,
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

    /*@MethodSource("getCorrectArguments")
    @ParameterizedTest
    @DisplayName("Generator")
    fun testGenerator(expectedFilePath: String, configPath: String, actualFileName: String) {
        createKtFile(configPath, "temp")
        assertEquals(File(expectedFilePath).readText(), File("temp/PcsPackage/$actualFileName.kt").readText())
        //File("temp/PcsPackage/$actualFileName.kt").delete()
    }*/

    @MethodSource("getIncorrectArguments")
    @ParameterizedTest
    @DisplayName("Generator exceptions")
    fun testGeneratorExceptions(configPath: String) {
        assertThrows<IllegalStateException> {
            createKtFile(configPath, "temp")
        }
    }
}