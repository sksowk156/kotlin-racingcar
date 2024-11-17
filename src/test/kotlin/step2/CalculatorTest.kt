package step2

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CalculatorTest {
    private val expressionValidator = ExpressionValidator()
    private val calculator = Calculator(expressionValidator)

    @ParameterizedTest(name = "입력: ''{0}'' => 예상 결과: ''{1}''")
    @CsvSource(
        "1 + 2, 3",
        "3 - 1, 2",
        "4 * 5, 20",
        "20 / 4, 5",
        "10 + 5 - 3, 12",
        "2 * 3 + 4, 10",
        "100 / 20 * 3, 15",
        "7 + 3 * 2 - 4 / 2, 8",
    )
    fun `정상적인 수식 입력 시 올바른 결과를 반환한다`(
        input: String,
        expected: String,
    ) {
        val result = calculator(input)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest(name = "빈 입력: ''{0}''")
    @ValueSource(strings = ["", "   "])
    fun `빈 문자열 입력 시 IllegalArgumentException을 던진다`(input: String) {
        assertThatThrownBy { calculator(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력 값이 없습니다.")
    }

    @ParameterizedTest(name = "잘못된 구조: ''{0}''")
    @ValueSource(strings = ["1 +", "2 - 3 *", "4 * 5 +"])
    fun `식의 토큰 개수가 짝수일 경우 IllegalArgumentException을 던진다`(input: String) {
        assertThatThrownBy { calculator(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("식의 형태가 올바르지 않습니다.")
    }

    @ParameterizedTest(name = "비정수 피연산자: ''{0}''")
    @ValueSource(strings = ["1 + a", "b - 2", "3 * five", "four / 2"])
    fun `피연산자가 정수가 아닐 경우 IllegalArgumentException을 던진다`(input: String) {
        assertThatThrownBy { calculator(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("정수가 아닙니다")
    }

    @ParameterizedTest(name = "지원되지 않는 연산자: ''{0}''")
    @ValueSource(strings = ["1 ^ 2", "3 & 4", "5 @ 6", "7 $ 8"])
    fun `지원되지 않는 연산자가 사용될 경우 IllegalArgumentException을 던진다`(input: String) {
        assertThatThrownBy { calculator(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("유효하지 않는 연산자 입니다.")
    }

    @ParameterizedTest(name = "0으로 나누기: ''{0}''")
    @ValueSource(strings = ["10 / 0", "5 / 0", "100 / 0"])
    fun `0으로 나눌 경우 IllegalArgumentException을 던진다`(input: String) {
        assertThatThrownBy { calculator(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("0으로는 나눌 수 없습니다.")
    }
}
