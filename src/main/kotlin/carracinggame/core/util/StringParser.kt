package carracinggame.core.util

interface StringParser {
    fun convertToInt(input: String): Int

    fun splitByComma(input: String): List<String>
}

class StringParserImpl : StringParser {
    override fun convertToInt(input: String): Int = input.toIntOrNull() ?: throw IllegalArgumentException("유효한 숫자를 입력해주세요.")

    override fun splitByComma(input: String): List<String> = input.split(",")
}
