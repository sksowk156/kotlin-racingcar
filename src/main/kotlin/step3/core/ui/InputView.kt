package step3.core.ui

class InputView(
    private val requestMessage: String,
    private val callback: (String) -> Unit,
) {
    operator fun invoke() {
        println(requestMessage)
        val input = readlnOrNull()?.trim()
        if (input.isNullOrEmpty()) throw IllegalArgumentException("입력이 없습니다.")
        callback(input)
    }
}
