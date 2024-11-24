package carracinggame.feature

sealed class GameIntent {
    data class InitializeGame(
        val carCountInput: String,
        val attemptCount: String,
    ) : GameIntent()

    data object UpdateCarState : GameIntent()
}
