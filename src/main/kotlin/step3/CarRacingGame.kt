package step3

import step3.feature.CarRacingGameView

fun main() {
    val carRacingGame = CarRacingGameView()
    try {
        carRacingGame()
    } catch (e: IllegalArgumentException) {
        println("오류: ${e.message}")
    }
}
