package step3.feature

import step3.core.ui.InputView
import step3.core.ui.ResultView

class CarRacingGame(
    private val viewModel: CarRacingGameViewModel = CarRacingGameViewModel(),
) {
    private val carCountView = InputView(CAR_COUNT)
    private val attemptCountView = InputView(ATTEMPT_COUNT)

    fun start() {
        progressGame(
            initializeGameState = { carCountInput, attemptCount ->
                viewModel.processIntent(
                    GameIntent.InitializeGame(carCountInput, attemptCount),
                )
            },
            updateGameState = { viewModel.processIntent(GameIntent.PerformAttempt) },
        )
    }

    private fun progressGame(
        initializeGameState: (String, String) -> Unit,
        updateGameState: () -> Unit,
    ) {
        val carCount = carCountView.getInputMessage()
        val attemptCount = attemptCountView.getInputMessage()

        // 입력된 대수로 게임 상태 초기화
        initializeGameState(carCount, attemptCount)

        ResultView(RESULT).render()
        repeat(viewModel.state.attemptCount) {
            // 게임 상태 업데이트
            updateGameState()
            viewModel.state.cars.forEach {
                ResultView(it.totalDistance).render()
            }
            ResultView("").render()
        }
    }

    private companion object {
        const val CAR_COUNT = "자동차 대수는 몇 대인가요?"
        const val ATTEMPT_COUNT = "시도할 횟수는 몇 회인가요?"
        const val RESULT = "실행 결과"
    }
}
