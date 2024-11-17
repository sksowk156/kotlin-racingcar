package step3.core.ui

class ResultView(private val outputMessage: String) {
    operator fun invoke() {
        println(outputMessage)
    }
}
