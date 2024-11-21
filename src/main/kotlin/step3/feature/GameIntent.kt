package step3.feature

sealed class GameIntent {
    data class InitializeGame(
        val carCountInput: String,
        val attemptCount: String,
    ) : GameIntent()

    data object PerformAttempt : GameIntent()
}
