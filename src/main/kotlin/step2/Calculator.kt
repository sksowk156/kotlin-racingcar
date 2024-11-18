package step2

class Calculator(
    private val expressionParser: ExpressionParser,
) {
    operator fun invoke(expression: String): String = calcaulate(expressionParser.parse(expression))

    private fun calcaulate(expressionTokens: List<String>): String {
        val firstOperand = expressionTokens[0].toInt()
        var result = firstOperand
        for (i in 2 until expressionTokens.size step 2) {
            val binaryOperator = Operator.findOperator(symbol = expressionTokens[i - 1])
            val operand = expressionTokens[i].toInt()
            result = binaryOperator.calculate(result, operand)
        }
        return result.toString()
    }
}
