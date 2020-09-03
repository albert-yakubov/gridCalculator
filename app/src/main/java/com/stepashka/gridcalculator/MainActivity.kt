package com.stepashka.gridcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var selectedOp = ""
    private var oldNum = ""
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // clearing
    private fun clear(){
        textviewResult.text = "0"
        textviewPreview.text = ""

    }
    fun clearInput(view: View) {
        view as Button
        clear()
        isNewOp = true
        textviewResult.text = "0"
        textviewPreview.text = ""
    }


    //handling clicks
    fun setNextNumber(oldNum: String, selection: TextView) : String {
        var number: String = oldNum
        when(selection.id){
            textView0.id -> {
                if (number == "0" || number.isEmpty()) number = "0"
                else number += 0
            }
            textView1.id -> {
                if (number == "0" || number.isEmpty()) number = "1"
                else number += 1
            }
            textView2.id -> {
                if (number == "0" || number.isEmpty()) number = "2"
                else number += 2
            }
            textView3.id -> {
                if (number == "0" || number.isEmpty()) number = "3"
                else number += 3
            }
            textView4.id -> {
                if (number == "0" || number.isEmpty()) number = "4"
                else number += 4
            }
            textView5.id -> {
                if (number == "0" || number.isEmpty()) number = "5"
                else number += 5
            }
            textView6.id -> {
                if (number == "0" || number.isEmpty()) number = "6"
                else number += 6
            }
            textView7.id -> {
                if (number == "0" || number.isEmpty()) number = "7"
                else number += 7
            }
            textView8.id -> {
                if (number == "0" || number.isEmpty()) number = "8"
                else number += 8
            }
            textView9.id -> {
                if (number == "0" || number.isEmpty()) number = "9"
                else number += 9
            }
            // handle the point:
            textViewDot.id -> {
                if (number == "") number = "0."
                else {
                    if(number.indexOf('.') == -1) number += "."
                }

            }
            // handling negatives
            textViewMinus.id -> {
                if (number == "0" || number.isEmpty()) number = "0"
                else {
                    if(number.first() == '-') number = number.substring(1, number.lastIndex + 1)
                    else number = "-$number"
                }
            }
        }
        previewEvent(textviewResult.text.toString())
        return number
    }

    // handling Ops
    fun opEvent(view: View){
        val selectOp = view as TextView
        when(selectOp.id){
            textViewPlus.id -> selectedOp = "+"
            textViewMinus.id -> selectedOp = "-"
            textViewDivide.id -> selectedOp = "/"
            textViewTimes.id -> selectedOp ="*"
        }
        isNewOp = true
        previewEvent(selectOp.text.toString())
        oldNum = textviewResult.text.toString()

    }

    // handling preview
    private fun previewEvent(result: String){
        textviewPreview.text = result
    }

    // handling equals
    fun equalsEvent(view: View) {
        view as TextView
        val newNumber = textviewResult.text.toString().toDouble()
        var answer: Double? = null
        if (selectedOp != "" && oldNum != "") {
            when (selectedOp) {
                "÷" -> answer = oldNum.toDouble() / newNumber
                "×" -> answer = oldNum.toDouble() * newNumber
                "-" -> answer = oldNum.toDouble() - newNumber
                "+" -> answer = oldNum.toDouble() + newNumber
            }
            textviewResult.text = answer.toString()
            previewEvent(textviewResult.text.toString())
            isNewOp = true
            oldNum = ""
            //that resets the selected operation
            selectedOp = ""
        }
    }

    //clicks:
    fun clickButtonEvent(view: View) {
        if (isNewOp) {
            clear()
        }
        isNewOp = false
        val selectNumber = view as TextView
        val currentNumber: String = textviewResult.text.toString()
        val newNumber: String = setNextNumber(currentNumber, selectNumber)
        textviewResult.text = newNumber
        previewEvent(textviewResult.text.toString())
    }

}