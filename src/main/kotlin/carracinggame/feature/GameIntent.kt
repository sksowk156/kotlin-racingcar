package carracinggame.feature

sealed class GameIntent {
    data class InitializeGameState(
        val carCountInput: String,
        val attemptCount: String,
    ) : GameIntent()

    data object UpdateCarState : GameIntent()

    data object UpdateWinner : GameIntent()
}
