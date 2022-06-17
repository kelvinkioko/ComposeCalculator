package compose.calculator.friday

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> performCalculation()
            CalculatorAction.Clear -> state = CalculatorState()
            CalculatorAction.Decimal -> enterDecimal()
            CalculatorAction.Delete -> performDelete()
            is CalculatorAction.Number -> enterNumber(number = action.number)
            is CalculatorAction.Operation -> enterOperation(operation = action.operation)
        }
    }

    private fun performCalculation() {
        val numberOne: Double? = state.numberOne.toDoubleOrNull()
        val numberTwo: Double? = state.numberTwo.toDoubleOrNull()

        if (numberOne != null && numberTwo != null) {
            val result = when (state.operation) {
                CalculatorOperation.Add -> numberOne + numberTwo
                CalculatorOperation.Divide -> numberOne / numberTwo
                CalculatorOperation.Multiply -> numberOne * numberTwo
                CalculatorOperation.Subtract -> numberOne - numberTwo
                null -> return
            }

            state = state.copy(
                numberOne = result.toString().take(15),
                operation = null,
                numberTwo = ""
            )
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.numberOne.contains(".") && state.numberOne.isNotBlank()) {
            state = state.copy(numberOne = state.numberOne + ".")
            return
        }
        if (state.operation != null && !state.numberTwo.contains(".") && state.numberTwo.isNotBlank()) {
            state = state.copy(numberTwo = state.numberTwo + ".")
            return
        }
    }

    private fun performDelete() {
        when {
            state.numberTwo.isNotBlank() -> state = state.copy(numberTwo = state.numberTwo.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.numberOne.isNotBlank() -> state = state.copy(numberOne = state.numberOne.dropLast(1))
        }
    }

    private fun enterNumber(number: Int) {
        state.operation?.let {
            if (state.numberTwo.length >= MAX_NUM_LENGTH)
                return
            state = state.copy(
                numberTwo = state.numberTwo + number
            )
        } ?: run {
            if (state.numberOne.length >= MAX_NUM_LENGTH)
                return
            state = state.copy(
                numberOne = state.numberOne + number
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.numberOne.isNotBlank())
            state = state.copy(operation = operation)
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}