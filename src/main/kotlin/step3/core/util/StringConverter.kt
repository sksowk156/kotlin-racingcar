package step3.core.util

interface StringConverter {
    fun convertToInt(input: String): Int
}

class StringConverterImpl : StringConverter {
    override fun convertToInt(input: String): Int = input.toIntOrNull() ?: throw IllegalArgumentException("유효한 숫자를 입력해주세요.")
}
