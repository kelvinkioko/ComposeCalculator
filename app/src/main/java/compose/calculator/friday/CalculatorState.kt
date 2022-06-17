package compose.calculator.friday

data class CalculatorState(
    val numberOne: String = "",
    val numberTwo: String = "",
    val operation: CalculatorOperation? = null
)
