package carracinggame

import carracinggame.feature.CarRacingGame

fun main() {
    val carRacingGame = CarRacingGame()
    try {
        carRacingGame.start()
    } catch (e: IllegalArgumentException) {
        println("오류: ${e.message}")
    }
}
