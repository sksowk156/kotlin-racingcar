package carracinggame.feature

import carracinggame.core.ui.InputView
import carracinggame.core.ui.ResultView

class CarRacingGame(
    private val viewModel: CarRacingGameViewModel = CarRacingGameViewModel(),
) {
    private val carNamesView = InputView(CAR_NAMES)
    private val attemptCountView = InputView(ATTEMPT_COUNT)

    fun start() {
        progressGame(
            initializeGameState = { carNamesInput, attemptCount ->
                viewModel.processIntent(
                    GameIntent.InitializeGameState(carNamesInput, attemptCount),
                )
            },
            updateCarState = { viewModel.processIntent(GameIntent.UpdateCarState) },
            updateWinner = { viewModel.processIntent(GameIntent.UpdateWinner) },
        )
    }

    private fun progressGame(
        initializeGameState: (String, String) -> Unit,
        updateCarState: () -> Unit,
        updateWinner: () -> Unit,
    ) {
        val carNames = carNamesView.getInputMessage()
        val attemptCount = attemptCountView.getInputMessage()

        // 입력된 대수로 게임 상태 초기화
        initializeGameState(carNames, attemptCount)

        ResultView(RESULT).render()
        repeat(viewModel.state.attemptCount) {
            // 게임 상태 업데이트
            updateCarState()
            viewModel.state.cars.forEach { car ->
                val currentDistance = "-".repeat(car.totalDistance)
                ResultView("${car.name} : $currentDistance").render()
            }
            ResultView("").render()
        }
        updateWinner()
        ResultView("${viewModel.state.winner}가 최종 우승했습니다.").render()
    }

    private companion object {
        const val CAR_NAMES = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."
        const val ATTEMPT_COUNT = "시도할 횟수는 몇 회인가요?"
        const val RESULT = "실행 결과"
    }
}
