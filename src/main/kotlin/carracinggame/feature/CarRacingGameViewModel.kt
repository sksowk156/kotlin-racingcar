package carracinggame.feature

import carracinggame.core.domain.CarMovementChecker
import carracinggame.core.model.Car
import carracinggame.core.model.GameState
import carracinggame.core.util.NumberGeneratorImpl
import carracinggame.core.util.StringConverter
import carracinggame.core.util.StringConverterImpl

class CarRacingGameViewModel(
    private val carMovementChecker: CarMovementChecker = CarMovementChecker(NumberGeneratorImpl()),
    private val stringConverter: StringConverter = StringConverterImpl(),
) {
    private var _state = GameState()
    val state: GameState get() = _state

    fun processIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.InitializeGame -> initializeCars(intent.carCountInput, intent.attemptCount)
            GameIntent.UpdateCarState -> performAttempt()
        }
    }

    private fun initializeCars(
        carCountInput: String,
        attemptCountInput: String,
    ) {
        val carCount = stringConverter.convertToInt(carCountInput)
        val attemptCount = stringConverter.convertToInt(attemptCountInput)

        _state =
            _state
                .copy(
                    cars = List(carCount) { idx -> Car(idx) },
                    attemptCount = attemptCount,
                )
    }

    private fun performAttempt() {
        val updatedCars =
            _state.cars.map { car ->
                if (carMovementChecker.shouldMove()) car.move()
                car
            }
        _state = _state.copy(cars = updatedCars)
    }
}
