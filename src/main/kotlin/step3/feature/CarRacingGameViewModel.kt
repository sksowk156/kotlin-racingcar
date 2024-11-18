package step3.feature

import step3.core.domain.CarUpdateChecker
import step3.core.model.CarRacingState
import step3.core.util.NumberGeneratorImpl

class CarRacingGameViewModel(
    private val carUpdateChecker: CarUpdateChecker = CarUpdateChecker(NumberGeneratorImpl()),
) {
    private var _state = CarRacingState()
    val state: CarRacingState get() = _state

    fun processIntent(intent: CarRacingIntent) {
        when (intent) {
            is CarRacingIntent.InitializeGame -> initializeCars(intent.carCountInput, intent.attemptCount)
            CarRacingIntent.PerformAttempt -> performAttempt()
        }
    }

    private fun initializeCars(
        carCountInput: String,
        attemptCountInput: String,
    ) {
        val carCount =
            carCountInput.toIntOrNull()
                ?: throw IllegalArgumentException("유효한 숫자를 입력해주세요.")

        val attemptCount =
            attemptCountInput.toIntOrNull()
                ?: throw IllegalArgumentException("유효한 숫자를 입력해주세요.")

        _state =
            _state
                .copy(
                    cars = List(carCount) { "" },
                    attemptCount = attemptCount,
                )
    }

    private fun performAttempt() {
        val updatedCars =
            _state.cars.map {
                if (carUpdateChecker()) "$it-" else it
            }
        _state = _state.copy(cars = updatedCars)
    }
}
