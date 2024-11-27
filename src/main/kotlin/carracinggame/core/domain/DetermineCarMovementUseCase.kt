package carracinggame.core.domain

import carracinggame.core.util.NumberGenerator
import carracinggame.core.util.NumberGeneratorImpl

class DetermineCarMovementUseCase(private val numberGenerator: NumberGenerator = NumberGeneratorImpl()) {
    operator fun invoke(): Boolean = numberGenerator.generateRandomNum() > 4
}
