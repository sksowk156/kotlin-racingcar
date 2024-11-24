package carracinggame.core.util

interface NumberGenerator {
    fun generateRandomNum(): Int
}

class NumberGeneratorImpl : NumberGenerator {
    override fun generateRandomNum(): Int = (0..9).random()
}
