package step3.feature

import step3.core.ui.InputView
import step3.core.ui.ResultView

class CarRacingGameView(
    private val viewModel: CarRacingGameViewModel = CarRacingGameViewModel(),
) {
    operator fun invoke() {
        startGame(
            initializeCars = { carCountInput, attemptCount ->
                viewModel.processIntent(
                    CarRacingIntent.InitializeGame(carCountInput, attemptCount),
                )
            },
            performAttempt = { viewModel.processIntent(CarRacingIntent.PerformAttempt) },
        )
    }

    private fun startGame(
        initializeCars: (String, String) -> Unit,
        performAttempt: () -> Unit,
    ) {
        // 자동차 대수 입력
        InputView(requestMessage = CAR_COUNT) { carCountInput ->
            // 횟수 입력
            InputView(requestMessage = ATTEMPT_COUNT) { attemptCountInput ->
                // 입력된 대수로 게임 상태 초기화
                initializeCars(carCountInput, attemptCountInput)

                println(RESULT)
                repeat(viewModel.state.attemptCount) {
                    // 게임 상태 업데이트
                    performAttempt()
                    viewModel.state.cars.forEach {
                        ResultView(it)()
                    }
                    println("")
                }
            }()
        }()
    }

    private companion object {
        const val CAR_COUNT = "자동차 대수는 몇 대인가요?"
        const val ATTEMPT_COUNT = "시도할 횟수는 몇 회인가요?"
        const val RESULT = "실행 결과"
    }
}
