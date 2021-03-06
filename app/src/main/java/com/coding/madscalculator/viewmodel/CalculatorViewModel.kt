package com.coding.madscalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.coding.madscalculator.evaluation.Evaluation
import java.lang.Exception
import kotlin.math.floor

class CalculatorViewModel:ViewModel() {
    val exp = MutableLiveData<String>()
    val answer = MutableLiveData<String>()

    init {
        exp.value = "0"
        answer.value = "0"
    }

    fun delete() {
        val string = exp.value!!
        if (string.length == 1 || (string.length == 2 && string[0] == '-'))
            reset()
        else {
            exp.value = string.substring(0, string.length - 1)
            reevaluate()
        }
    }

    private fun reevaluate() {
        val text = getFormatted()
        Log.d("number", text)

        try {
            val result = Evaluation.evaluate(text)
            if (floor(result.toDouble()) == result.toDouble())
                answer.value = result.toString()
            else
                answer.value = result.toString()
        } catch (e: Exception) {
            answer.value="Invalid"
        }
    }

    private fun getFormatted(): String {
        var string = exp.value!!
        Log.d("number", string)
        if (string[string.length - 1] == '.')
            string = string.substring(0, string.length - 1)

        while (!string[string.length - 1].isDigit() && string.isNotEmpty())
            string = string.substring(0, string.length - 1)

        var countO = 0
        var countC = 0
        for (i in string) {
            if (i == '(')
                countO++
            if (i == ')')
                countC++
        }
        if (countC == countO)
            return string
        for (i in 1..countO - countC)
            string += ")"

        return string
    }

    fun equate() {
        exp.value = answer.value
    }

    fun reset() {
        exp.value = "0"
        answer.value = "0"
    }

    fun addOperator(o: Char) {
        if (o == '-' && exp.value == "0") {
            exp.value = "-"
            answer.value = "-"
            return
        }
        var string = exp.value!!
        if (string[string.length - 1] == '.')
            string = string.substring(0, string.length - 1)
        if (string[string.length - 1] == '(' && o != '-')
            return
        if (!string[string.length - 1].isDigit() && o != '-')
            string = string.substring(0, string.length - 1)
        string += o.toString()
        exp.value = string
    }

    fun addNumber(n: String) {
        var string = exp.value!!
        if (n == ".") {
            var lastDot = false
            for (i in string) {
                if (i.isDigit())
                    continue
                lastDot = i == '.'
            }
            if (lastDot)
                return
        }
        if (string.trim() == "0")
            string = ""
        if (n == "." && string == "")
            string = "0"
        string += n
        exp.value = string
        Log.d("number", exp.value!!)
        reevaluate()
    }


}