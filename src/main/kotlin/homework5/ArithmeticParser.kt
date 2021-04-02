package homework5

import java.io.File

abstract class ParserNode(val content: String, val level: Int) {
    abstract val leftChild: ParserNode?
    abstract val rightChild: ParserNode?
    abstract val arithmeticValue: Int
}

class ParserOperation(
    content: String,
    level: Int,
    override val leftChild: ParserNode,
    override val rightChild: ParserNode
) : ParserNode(content, level) {
    override val arithmeticValue = when (content) {
        "+" -> leftChild.arithmeticValue + rightChild.arithmeticValue
        "-" -> leftChild.arithmeticValue - rightChild.arithmeticValue
        "*" -> leftChild.arithmeticValue * rightChild.arithmeticValue
        "/" -> leftChild.arithmeticValue / rightChild.arithmeticValue
        else -> 0
    }

    override fun toString(): String {
        return "....".repeat(level) + content + "\n" + leftChild.toString() + rightChild.toString()
    }
}

class ParserOperand(content: String, level: Int) : ParserNode(content, level) {
    override val leftChild: ParserNode? = null
    override val rightChild: ParserNode? = null
    override val arithmeticValue = content.toInt()
    override fun toString(): String {
        return "....".repeat(level) + content + "\n"
    }
}

class ParserTree(expression: String) {
    private val elements: List<String> = expression
        .replace("(", "")
        .replace(")", "")
        .split(" ")

    private val root: ParserNode = recursiveParseTree(position = 0, level = 0).first
    private val tree: String = this.root.toString()
    val arithmeticValue = this.root.arithmeticValue

    private fun recursiveParseTree(position: Int, level: Int): Pair<ParserNode, Int> {
        return when {
            this.elements[position] in listOf("+", "-", "*", "/") -> {
                val operationType = this.elements[position]

                val parsedFirstOperand = recursiveParseTree(position + 1, level + 1)
                val leftOperand = parsedFirstOperand.first
                var newPosition = parsedFirstOperand.second

                val parsedSecondOperand = recursiveParseTree(newPosition, level + 1)
                val rightOperand = parsedSecondOperand.first
                newPosition = parsedSecondOperand.second

                Pair(ParserOperation(operationType, level, leftOperand, rightOperand), newPosition)
            }
            else -> Pair(ParserOperand(elements[position], level), position + 1)
        }
    }

    fun printTreeToFile(path: String) {
        val file = File(path)
        file.writeText(this.tree)
    }
}

fun main() {
    val testParser = ParserTree("(+ (* 2 (- 5 2)) (/ 20 2))")
    testParser.printTreeToFile("temp.txt")
}