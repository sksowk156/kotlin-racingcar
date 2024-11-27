package carracinggame.core.util

import carracinggame.core.domain.DetermineCarMovementUseCase
import carracinggame.core.model.Car

class CarFactory(
    private val determineCarMovementUseCase: DetermineCarMovementUseCase,
) {
    fun create(name: String): Car = Car(name, determineCarMovementUseCase)
}
