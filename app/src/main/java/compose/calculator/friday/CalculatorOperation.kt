package compose.calculator.friday

sealed class CalculatorOperation(val symbol: String) {
    object Add: CalculatorOperation(symbol = "+")
    object Subtract: CalculatorOperation(symbol = "-")
    object Multiply: CalculatorOperation(symbol = "*")
    object Divide: CalculatorOperation(symbol = "/")
}
