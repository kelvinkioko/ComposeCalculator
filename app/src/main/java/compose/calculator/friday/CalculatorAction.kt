package compose.calculator.friday

sealed class CalculatorActions {
    data class Number(val number: Int): CalculatorActions()
    object Clear: CalculatorActions()
    object Delete: CalculatorActions()
    object
}
