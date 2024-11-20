package step3

import step3.feature.CarRacingGame

fun main() {
    val carRacingGame = CarRacingGame()
    try {
        carRacingGame.start()
    } catch (e: IllegalArgumentException) {
        println("오류: ${e.message}")
    }
}
