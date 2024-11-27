package carracinggame.core.util

object StringParser {
    fun convertToInt(input: String): Int = input.toIntOrNull() ?: throw IllegalArgumentException("유효한 숫자를 입력해주세요.")

    fun splitByComma(input: String): List<String> = input.split(",")

    fun combineByComma(input: List<String>): String = input.joinToString(separator = ", ") { it }
}
