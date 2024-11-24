package carracinggame.core.domain

import carracinggame.core.util.NumberGenerator

class DetermineCarMovementUseCase(private val numberGenerator: NumberGenerator) {
    operator fun invoke(): Boolean = numberGenerator.generateRandomNum() > 4
}
