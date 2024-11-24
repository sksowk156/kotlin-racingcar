package carracinggame.core.domain

import carracinggame.core.util.NumberGenerator

class CarMovementChecker(private val numberGenerator: NumberGenerator) {
    fun shouldMove(): Boolean = numberGenerator.generateRandomNum() > 4
}
