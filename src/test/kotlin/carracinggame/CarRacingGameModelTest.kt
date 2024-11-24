package carracinggame

import carracinggame.core.domain.CarMovementChecker
import carracinggame.feature.CarRacingGameViewModel
import carracinggame.feature.GameIntent
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CarRacingGameModelTest {
    @Test
    fun `InitializeGame Intent를 처리하면 상태가 올바르게 초기화된다`() {
        val viewModel =
            CarRacingGameViewModel(CarMovementChecker(TestNumberGeneratorImpl(listOf(3, 3, 3, 3, 3, 3))))

        val carCountInput = "2"
        val attemptCountInput = "3"
        val intent = GameIntent.InitializeGame(carCountInput, attemptCountInput)

        viewModel.processIntent(intent)

        val state = viewModel.state
        assertThat(state.cars).hasSize(2).allMatch { it.totalDistance.isEmpty() }
        assertThat(state.attemptCount).isEqualTo(3)
    }

    @Test
    fun `PerformAttempt Intent를 처리하면 자동차 상태가 업데이트된다`() {
        val viewModel =
            CarRacingGameViewModel(CarMovementChecker(TestNumberGeneratorImpl(listOf(3, 5, 3, 5, 3, 5))))

        val carCountInput = "2"
        val attemptCountInput = "3"
        val initializeIntent = GameIntent.InitializeGame(carCountInput, attemptCountInput)
        viewModel.processIntent(initializeIntent)

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo("") // 첫 번째 자동차는 이동하지 않음
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo("-") // 두 번째 자동차는 이동

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo("") // 첫 번째 자동차는 이동하지 않음
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo("--") // 두 번째 자동차는 이동

        viewModel.processIntent(GameIntent.UpdateCarState)
        assertThat(viewModel.state.cars[0].totalDistance).isEqualTo("") // 첫 번째 자동차는 이동하지 않음
        assertThat(viewModel.state.cars[1].totalDistance).isEqualTo("---") // 두 번째 자동차는 이동
    }

    @Test
    fun `잘못된 carCountInput으로 InitializeGame Intent를 처리하면 예외가 발생한다`() {
        val viewModel =
            CarRacingGameViewModel(CarMovementChecker(TestNumberGeneratorImpl(listOf(0))))

        val carCountInput = "invalid"
        val attemptCountInput = "5"
        val intent = GameIntent.InitializeGame(carCountInput, attemptCountInput)

        assertThatThrownBy { viewModel.processIntent(intent) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("유효한 숫자를 입력해주세요.")
    }

    @Test
    fun `잘못된 attemptCountInput으로 InitializeGame Intent를 처리하면 예외가 발생한다`() {
        val viewModel =
            CarRacingGameViewModel(CarMovementChecker(TestNumberGeneratorImpl(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))))

        val carCountInput = "3"
        val attemptCountInput = "invalid"
        val intent = GameIntent.InitializeGame(carCountInput, attemptCountInput)

        assertThatThrownBy { viewModel.processIntent(intent) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("유효한 숫자를 입력해주세요.")
    }
}
