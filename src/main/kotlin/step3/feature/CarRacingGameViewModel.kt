package step3.feature

import step3.core.domain.GetCurrentTotalDistance
import step3.core.model.Car
import step3.core.model.GameState
import step3.core.util.NumberGeneratorImpl
import step3.core.util.StringConverter
import step3.core.util.StringConverterImpl

class CarRacingGameViewModel(
    private val getCurrentTotalDistance: GetCurrentTotalDistance = GetCurrentTotalDistance(NumberGeneratorImpl()),
    private val stringConverter: StringConverter = StringConverterImpl(),
) {
    private var _state = GameState()
    val state: GameState get() = _state

    fun processIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.InitializeGame -> initializeCars(intent.carCountInput, intent.attemptCount)
            GameIntent.PerformAttempt -> performAttempt()
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
                    cars = List(carCount) { Car() },
                    attemptCount = attemptCount,
                )
    }

    private fun performAttempt() {
        val updatedCars =
            _state.cars.map { car ->
                Car(totalDistance = getCurrentTotalDistance(car))
            }
        _state = _state.copy(cars = updatedCars)
    }
}
