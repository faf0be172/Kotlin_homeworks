package homework5

import java.io.File

abstract class ParserNode(val content: String) {
    abstract val leftChild: ParserNode?
    abstract val rightChild: ParserNode?
    abstract val arithmeticValue: Int
}

class ParserOperation(
    content: String,
    override val leftChild: ParserNode,
    override val rightChild: ParserNode
    ) : ParserNode(content) {
    override val arithmeticValue = when (content) {
        "+" -> leftChild.arithmeticValue + rightChild.arithmeticValue
        "-" -> leftChild.arithmeticValue - rightChild.arithmeticValue
        "*" -> leftChild.arithmeticValue * rightChild.arithmeticValue
        "/" -> leftChild.arithmeticValue / rightChild.arithmeticValue
        else -> 0
    }
}

class ParserOperand(content: String) : ParserNode(content) {
    override val leftChild: ParserNode? = null
    override val rightChild: ParserNode? = null
    override val arithmeticValue = content.toInt()
}

class ParserTree(expression: String) {
    private val elements: List<String> = expression
        .replace("(", "")
        .replace(")", "")
        .split(" ")

    private val root: ParserNode = recursiveParseTree(0).first
    private val tree: String? = recursivePrintTree(this.root, 0)
    val arithmeticValue = this.root.arithmeticValue

    private fun recursiveParseTree(position: Int): Pair<ParserNode, Int> {
        return when {
            this.elements[position] in listOf("+", "-", "*", "/") -> {
                val operationType = this.elements[position]

                val parsedFirstOperand = recursiveParseTree(position + 1)
                val leftOperand = parsedFirstOperand.first
                var newPosition = parsedFirstOperand.second

                val parsedSecondOperand = recursiveParseTree(newPosition)
                val rightOperand = parsedSecondOperand.first
                newPosition = parsedSecondOperand.second

                Pair(ParserOperation(operationType, leftOperand, rightOperand), newPosition)
            }
            else -> Pair(ParserOperand(elements[position]), position + 1)
        }
    }

    fun printTree(path: String) {
        val file = File(path)
        file.writeText(this.tree ?: "tree is empty")
    }

    private fun recursivePrintTree(node: ParserNode?, level: Int): String? {
        node?.let {
            var treeFragment = ("....".repeat(level) + node.content + '\n')
            if (node.leftChild != null && node.rightChild != null) {
                treeFragment += recursivePrintTree(node.leftChild, level + 1)
                treeFragment += recursivePrintTree(node.rightChild, level + 1)
            }
            return treeFragment
        }
        return null
    }
}
