package homework5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

internal class ArithmeticParserTest {
    companion object {
        @JvmStatic
        fun getCorrectExpressions(): Stream<Arguments> = Stream.of(
            Arguments.of("expression1", 16),
            Arguments.of("expression2", 10)
        )
    }

    @ParameterizedTest
    @MethodSource("getCorrectExpressions")
    fun testParser(expressionFileName: String, expectedValue: Int, @TempDir tempTxtFile: File) {
        val expressionPath = javaClass.getResource("$expressionFileName/$expressionFileName.txt").path
        val expectedTreePath = javaClass.getResource("$expressionFileName/expected.txt").path
        val tempFile = tempTxtFile.resolve("temp.txt")

        val testParser = ParserTree(File(expressionPath).readText())
        testParser.printTreeToFile(tempFile.path)

        assertEquals(expectedValue, testParser.arithmeticValue)
        assertEquals(File(expectedTreePath).readText().replace("\r\n", "\n"), tempFile.readText())
        tempFile.delete()
    }
}
