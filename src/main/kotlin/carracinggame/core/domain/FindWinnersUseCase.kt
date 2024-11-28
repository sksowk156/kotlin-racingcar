package carracinggame.core.domain

import carracinggame.core.model.Car

class FindWinnersUseCase {
    operator fun invoke(cars: List<Car>): List<Car> {
        val maxDistance = cars.maxOf { it.totalDistance }
        return cars.filter { it.totalDistance == maxDistance }
    }
}
