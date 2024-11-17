package step2

class Calculator(
    private val expressionValidator: ExpressionValidator,
) {
    operator fun invoke(input: String): String {
        val expression =
            input
                .trim()
                .split(" ")
                .filterNot { it.isEmpty() }

        expressionValidator.validate(expression)

        var result = expression[0].toInt()
        for (i in 2 until expression.size step 2) {
            val operator = Operator.findOperator(operator = expression[i - 1])
            val operand = expression[i].toInt()
            result = calcaulate(result, operator, operand)
        }

        return result.toString()
    }

    private fun calcaulate(
        firstOperand: Int,
        operator: Operator,
        secondOperand: Int,
    ): Int = operator.calculate(firstOperand, secondOperand)
}
