package step2

class ExpressionParser {
    fun parse(expression: String): List<String> {
        val tokens =
            expression
                .trim()
                .split(" ")
                .filterNot { it.isEmpty() }

        return validate(tokens)
    }

    private fun validate(tokens: List<String>): List<String> {
        checkEmpty(tokens)
        checkStructure(tokens)
        checkTokens(tokens)
        return tokens
    }

    private fun checkEmpty(tokens: List<String>) {
        if (tokens.isEmpty()) throw IllegalArgumentException("입력 값이 없습니다.")
    }

    private fun checkStructure(tokens: List<String>) {
        if (tokens.size % 2 == 0) {
            throw IllegalArgumentException("식의 형태가 올바르지 않습니다.")
        }
    }

    private fun checkTokens(tokens: List<String>) {
        for (idx in tokens.indices) {
            if (idx % 2 == 0) {
                tokens[idx].toIntOrNull()
                    ?: throw IllegalArgumentException("정수가 아닙니다")
            } else {
                Operator.findOperator(tokens[idx])
            }
        }
    }
}
