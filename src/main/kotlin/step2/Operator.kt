package step2

enum class Operator(val operator: String, val calculate: (Int, Int) -> Int) {
    ADDITION(
        "+",
        { firstNum, secondNum -> firstNum + secondNum },
    ),
    SUBTRACTION(
        "-",
        { firstNum, secondNum -> firstNum - secondNum },
    ),
    MULTIPLICATION(
        "*",
        { firstNum, secondNum -> firstNum * secondNum },
    ),
    DIVISION(
        "/",
        { firstNum, secondNum ->
            if (secondNum == 0) throw IllegalArgumentException("0으로는 나눌 수 없습니다.")
            firstNum / secondNum
        },
    ),
    ;

    companion object {
        private val operators: List<Operator> = entries

        fun findOperator(operator: String): Operator =
            operators.find { it.operator == operator }
                ?: throw IllegalArgumentException("유효하지 않는 연산자 입니다.")
    }
}