package step3.core.ui

class InputView(private val requestMessage: String) {
    fun getInputMessage(): String {
        println(requestMessage)
        val input = readlnOrNull()?.trim()
        if (input.isNullOrEmpty()) throw IllegalArgumentException("입력이 없습니다.")
        return input
    }
}