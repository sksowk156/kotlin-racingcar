package step3.core.domain

import step3.core.util.NumberGenerator

class CarUpdateChecker(private val numberGenerator: NumberGenerator) {
    operator fun invoke(): Boolean = numberGenerator.generateRandomNum() > 4
}
