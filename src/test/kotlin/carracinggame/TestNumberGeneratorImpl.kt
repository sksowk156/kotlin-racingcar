package carracinggame

import carracinggame.core.util.NumberGenerator

class TestNumberGeneratorImpl(private val numbers: List<Int>) : NumberGenerator {
    private var index = 0

    override fun generateRandomNum(): Int {
        val number = numbers[index % numbers.size]
        index++
        return number
    }
}
