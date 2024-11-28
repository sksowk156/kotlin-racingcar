package carracinggame.feature

import carracinggame.core.ui.InputView
import carracinggame.core.ui.ResultView

class CarRacingGame(
    private val controller: CarRacingGameController = CarRacingGameController(),
) {
    private val carNamesView = InputView(CAR_NAMES)
    private val attemptCountView = InputView(ATTEMPT_COUNT)

    fun start() {
        initailizeGame()

        processGame()

        ResultView("${controller.formatWinnerNames()}가 최종 우승했습니다.").render()
    }

    private fun initailizeGame() {
        val carNames = carNamesView.getInputMessage()
        val attemptCount = attemptCountView.getInputMessage()

        controller.initializeGameState(carNames, attemptCount)
    }

    private fun processGame() {
        ResultView(RESULT).render()
        repeat(controller.attemptCount) {
            controller.updateCarDistance()
            controller.cars.forEach { car ->
                val currentDistance = DISTANCE_INDICATOR.repeat(car.totalDistance)
                ResultView("${car.name} : $currentDistance").render()
            }
            ResultView().render()
        }
    }

    private companion object {
        const val CAR_NAMES = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."
        const val ATTEMPT_COUNT = "시도할 횟수는 몇 회인가요?"
        const val RESULT = "실행 결과"
        const val DISTANCE_INDICATOR = "-"
    }
}
