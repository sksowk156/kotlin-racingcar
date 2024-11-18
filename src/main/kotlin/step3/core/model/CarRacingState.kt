package step3.core.model

data class CarRacingState(
    val cars: List<String> = emptyList(),
    val attemptCount: Int = 0,
)
