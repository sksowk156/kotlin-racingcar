package carracinggame.core.model

data class GameState(
    val cars: List<Car> = emptyList(),
    val attemptCount: Int = 0,
)
