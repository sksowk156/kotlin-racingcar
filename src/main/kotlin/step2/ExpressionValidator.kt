package step2

class ExpressionValidator {
    fun validate(expression: List<String>) {
        checkExpressionEmpty(expression)
        checkExpressionStructure(expression)
        checkExpressionTokens(expression)
    }

    private fun checkExpressionEmpty(expression: List<String>) {
        if (expression.isEmpty()) throw IllegalArgumentException("입력 값이 없습니다.")
    }

    private fun checkExpressionStructure(expression: List<String>) {
        if (expression.size % 2 == 0) {
            throw IllegalArgumentException("식의 형태가 올바르지 않습니다.")
        }
    }

    private fun checkExpressionTokens(expression: List<String>) {
        for (idx in expression.indices) {
            if (idx % 2 == 0) {
                expression[idx].toIntOrNull()
                    ?: throw IllegalArgumentException("정수가 아닙니다")
            } else {
                Operator.findOperator(expression[idx])
            }
        }
    }
}
