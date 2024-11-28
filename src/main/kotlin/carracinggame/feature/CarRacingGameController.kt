package carracinggame.feature

import carracinggame.core.domain.DetermineCarMovementUseCase
import carracinggame.core.domain.FindWinnersUseCase
import carracinggame.core.model.Car
import carracinggame.core.util.CarFactory
import carracinggame.core.util.StringParser

class CarRacingGameController(
    private val findWinnersUseCase: FindWinnersUseCase = FindWinnersUseCase(),
    determineCarMovementUseCase: DetermineCarMovementUseCase = DetermineCarMovementUseCase(),
) {
    private val carFactory: CarFactory = CarFactory(determineCarMovementUseCase)
    var cars: List<Car> = emptyList()
        private set
    var attemptCount: Int = 0
        private set

    fun initializeGameState(
        carNamesInput: String,
        attemptCountInput: String,
    ) {
        val carNames = StringParser.splitByComma(carNamesInput)
        val attemptCounts = StringParser.convertToInt(attemptCountInput)

        cars = carNames.map { carFactory.create(it) }
        attemptCount = attemptCounts
    }

    fun updateCarDistance() {
        cars.forEach { it.updateCurrentDistance() }
    }

    fun formatWinnerNames(): String {
        val winners = findWinnersUseCase(cars)
        return StringParser.combineByComma(winners.map { it.name })
    }
}
