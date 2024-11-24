package carracinggame.feature

import carracinggame.core.domain.DetermineCarMovementUseCase
import carracinggame.core.domain.FindWinnersUseCase
import carracinggame.core.model.Car
import carracinggame.core.model.GameState
import carracinggame.core.util.NumberGeneratorImpl
import carracinggame.core.util.StringParser
import carracinggame.core.util.StringParserImpl

class CarRacingGameViewModel(
    private val determineCarMovementUseCase: DetermineCarMovementUseCase =
        DetermineCarMovementUseCase(NumberGeneratorImpl()),
    private val findWinnersUseCase: FindWinnersUseCase,
    private val stringParser: StringParser = StringParserImpl(),
) {
    private var _state = GameState()
    val state: GameState get() = _state

    fun processIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.InitializeGameState -> initializeGameState(intent.carCountInput, intent.attemptCount)
            GameIntent.UpdateCarState -> updateCarState()
            GameIntent.UpdateWinner -> updateWinner()
        }
    }

    private fun initializeGameState(
        carNamesInput: String,
        attemptCountInput: String,
    ) {
        val carNames = stringParser.splitByComma(carNamesInput)
        val attemptCount = stringParser.convertToInt(attemptCountInput)

        _state =
            _state
                .copy(
                    cars = carNames.map { Car(name = it) },
                    attemptCount = attemptCount,
                )
    }

    private fun updateCarState() {
        val updatedCars =
            _state.cars.map { car ->
                if (determineCarMovementUseCase()) car.move()
                car
            }
        _state = _state.copy(cars = updatedCars)
    }

    private fun updateWinner() {
        val winnerNames = findWinnersUseCase(_state.cars).joinToString(separator = ", ") { it.name }
        _state = _state.copy(winner = winnerNames)
    }
}
