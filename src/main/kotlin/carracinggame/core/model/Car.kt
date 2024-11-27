package carracinggame.core.model

import carracinggame.core.domain.DetermineCarMovementUseCase

class Car(
    val name: String,
    private val determineCarMovementUseCase: DetermineCarMovementUseCase,
) {
    init {
        require(name.length <= 5) { "자동차 이름은 5자를 초과할 수 없습니다" }
    }

    var totalDistance: Int = 0
        private set

    fun updateCurrentDistance() {
        if (determineCarMovementUseCase()) {
            totalDistance++
        }
    }
}
