package carracinggame.core.model

data class Car(
    val id: Int,
) {
    var totalDistance: Int = 0
        private set

    fun move() {
        this.totalDistance++
    }
}
