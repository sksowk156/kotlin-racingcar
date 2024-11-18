package step3.feature

sealed class CarRacingIntent {
    data class InitializeGame(
        val carCountInput: String,
        val attemptCount: String,
    ) : CarRacingIntent()

    data object PerformAttempt : CarRacingIntent()
}
