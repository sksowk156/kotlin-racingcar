package carracinggame.core.model

data class Car(
    val name: String,
) {
    var totalDistance: Int = 0
        private set

    fun move() {
        this.totalDistance++
    }
}
