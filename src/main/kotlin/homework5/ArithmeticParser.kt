package homework5

import java.io.File

class ParserNode(val content: String) {
    var leftChild: ParserNode? = null
    var rightChild: ParserNode? = null
}

class ParserTree(expression: String) {
    private val elements: List<String> = expression
        .replace("(", "")
        .replace(")", "")
        .split(" ")

    private var root: ParserNode = recursiveParseTree(elements, 0).first

    private fun recursiveParseTree(elements: List<String>, position: Int): Pair<ParserNode, Int> {
        return when {
            elements[position] in listOf("+", "-", "*", "/") -> {
                val node = ParserNode(elements[position])

                val firstOperand = recursiveParseTree(elements, position + 1)
                node.leftChild = firstOperand.first

                val secondOperand = recursiveParseTree(elements, firstOperand.second)
                node.rightChild = secondOperand.first

                Pair(node, secondOperand.second)
            }
            else -> Pair(ParserNode(elements[position]), position + 1)
        }
    }

    val arithmeticValue = recursiveProcessTree(this.root)

    private fun recursiveProcessTree(node: ParserNode?): Int {
        return when (node?.content) {
            "+" -> recursiveProcessTree(node.leftChild) + recursiveProcessTree(node.rightChild)
            "-" -> recursiveProcessTree(node.leftChild) - recursiveProcessTree(node.rightChild)
            "*" -> recursiveProcessTree(node.leftChild) * recursiveProcessTree(node.rightChild)
            "/" -> recursiveProcessTree(node.leftChild) / recursiveProcessTree(node.rightChild)
            else -> node?.content?.toInt() ?: 0
        }
    }

    fun printTree(path: String) {
        val file = File(path)
        file.writeText(recursivePrintTree(this.root, 0) ?: "tree is empty")
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

fun main() {
    val testParser = ParserTree("(* (+ 1 1) 2)")
    testParser.printTree("temp.txt")
}