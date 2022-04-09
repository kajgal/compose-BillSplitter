package com.example.jettip

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class TipViewModel : ViewModel() {

    // validate user input
    private val billInput = mutableStateOf("0.00")
    private val billCorrect = mutableStateOf(true)

    private val moneyAmount = mutableStateOf(0.0f)
    private val splitAmount = mutableStateOf(1)
    private val tipPercent = mutableStateOf(0.0f)

    fun setBillInput(bill: String) {
        billInput.value = bill

        val money = bill.toFloatOrNull()

        if (money == null) {
            moneyAmount.value = 0.0f
            billCorrect.value = false
        }
        else {
            moneyAmount.value = money
            billCorrect.value = true
        }
    }

    fun getBillInput(): String {
        return billInput.value
    }

    fun isBillValid(): Boolean {
        return billCorrect.value
    }

    fun setTipPercent(tip: Float) {
        tipPercent.value = tip
    }

    fun getTipPercent(): Float {
        return tipPercent.value
    }

    fun incrementSplit() {
        splitAmount.value += 1
    }

    fun decrementSplit() {
        if (splitAmount.value > 1)
            splitAmount.value -= 1
    }

    fun getSplit() : Int {
        return splitAmount.value
    }

    fun getTotalPerPerson() : Float {
        val percentageNormalized = (tipPercent.value * 100).roundToInt() / 100.0f
        return (moneyAmount.value + moneyAmount.value * percentageNormalized) / splitAmount.value
    }
}