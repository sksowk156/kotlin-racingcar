package step3.core.domain

import step3.core.model.Car
import step3.core.util.NumberGenerator

class GetCurrentTotalDistance(private val numberGenerator: NumberGenerator) {
    operator fun invoke(car: Car): String =
        if (shouldMove()) {
            car.totalDistance + DISTANCE_INDICATOR
        } else {
            car.totalDistance
        }

    private fun shouldMove(): Boolean = numberGenerator.generateRandomNum() > 4

    private companion object {
        const val DISTANCE_INDICATOR = "-"
    }
}
