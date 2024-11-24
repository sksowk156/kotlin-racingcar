package carracinggame

import carracinggame.core.domain.DetermineCarMovementUseCase
import carracinggame.feature.CarRacingGameViewModel
import carracinggame.feature.GameIntent
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CarRacingGameModelTest {
    @Test
    fun `InitializeGame Intent를 처리하면 상태가 올바르게 초기화된다`() {
        val viewModel =
            CarRacingGameViewModel(
                DetermineCarMovementUseCase(TestNumberGeneratorImpl(listOf(3, 3, 3, 3, 3, 3))),
            )

        val carNamesInput = "car1,car2"
        val attemptCountInput = "3"
        val intent = GameIntent.InitializeGameState(carNamesInput, attemptCountInput)

        viewModel.processIntent(intent)

        val state = viewModel.state
        assertThat(state.cars)
            .hasSize(2)
            .extracting<String> { it.name }
            .containsExactly("car1", "car2")
        assertThat(state.cars).allMatch { it.totalDistance == 0 }
        assertThat(state.attemptCount).isEqualTo(3)
    }

    @Test
    fun `자동차 이름이 5자를 초과하면 예외가 발생한다`() {
        val viewModel =
            CarRacingGameViewModel(
                DetermineCarMovementUseCase(TestNumberGeneratorImpl(listOf(0))),
            )

        val carNamesInput = "longname1,car2"
        val attemptCountInput = "5"
        val intent = GameIntent.InitializeGameState(carNamesInput, attemptCountInput)

        assertThatThrownBy { viewModel.processIntent(intent) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("자동차 이름은 5자를 초과할 수 없습니다.")
    }

    @Test
    fun `PerformAttempt Intent를 처리하면 자동차 상태가 업데이트된다`() {
        val viewModel =
            CarRacingGameViewModel(
                DetermineCarMovementUseCase(TestNumberGeneratorImpl(listOf(3, 5, 3, 5, 3, 5))),
            )

        val carNamesInput = "car1,car2"
        val attemptCountInput = "3"
        val initializeIntent = GameIntent.InitializeGameState(carNamesInput, attemptCountInput)
        viewModel.processIntent(initializeIntent)

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo(0) // 첫 번째 자동차는 이동하지 않음
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo(1) // 두 번째 자동차는 이동

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo(0)
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo(2)

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo(0)
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo(3)
    }

    @Test
    fun `게임 종료 후 우승자가 올바르게 결정된다`() {
        val viewModel =
            CarRacingGameViewModel(
                DetermineCarMovementUseCase(TestNumberGeneratorImpl(listOf(5, 5, 5, 5, 5, 5))),
            )

        val carNamesInput = "car1,car2"
        val attemptCountInput = "3"
        val initializeIntent = GameIntent.InitializeGameState(carNamesInput, attemptCountInput)
        viewModel.processIntent(initializeIntent)

        repeat(3) {
            viewModel.processIntent(GameIntent.UpdateCarState)
        }

        viewModel.processIntent(GameIntent.UpdateWinner)

        assertThat(viewModel.state.winner).isEqualTo("car1, car2") // Both cars have same distance
    }

    @Test
    fun `잘못된 attemptCountInput으로 InitializeGame Intent를 처리하면 예외가 발생한다`() {
        val viewModel =
            CarRacingGameViewModel(
                DetermineCarMovementUseCase(TestNumberGeneratorImpl(listOf(0))),
            )

        val carNamesInput = "car1,car2"
        val attemptCountInput = "invalid"
        val intent = GameIntent.InitializeGameState(carNamesInput, attemptCountInput)

        assertThatThrownBy { viewModel.processIntent(intent) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("유효한 숫자를 입력해주세요.")
    }
}