package com.example.mytipapplast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private var saveInputTip = "0"
    private val tage = "tage value"   // use Log.i search value
    private var saveInputAmount = "0"

    private lateinit var editTextTip: TextInputEditText

    // view bill amount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutAmountEnter =
            findViewById<TextInputLayout>(R.id.LayoutAmountEnter) // get Amount val  declare
        val editTextAmount = layoutAmountEnter.editText

        val layoutTipEnter =
            findViewById<TextInputLayout>(R.id.LayoutTipEnter) // get Tip val declare
        editTextTip = layoutTipEnter.editText as TextInputEditText

        val viewTotalBill = findViewById<TextView>(R.id.ViewTotalBill) //view total bill amount

        val viewTip = findViewById<TextView>(R.id.viewTip)           //view Tip
        val viewBill = findViewById<TextView>(R.id.viewBill)

        if (editTextAmount != null) {
            val enterAmount = editTextAmount.text ?: "0"
            editTextAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                open override fun afterTextChanged(p0: Editable?) {

                    // set the empty $0.00 the total bill ( set default value for total amount $0.00)
                    saveInputAmount = if (p0.toString().isNotEmpty()) p0.toString() else "0.00"

                    viewBill.setText(if (saveInputAmount !="0") saveInputAmount else "$0.00" )
                    calculateTipAmountAndTotalBillAmount(saveInputAmount, viewTip, viewTotalBill)

                }

            })
        }
        if (editTextTip != null) {
            val enterTip = editTextTip.text ?: ""
            editTextTip.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    saveInputTip =
                        if (p0.toString().isNotEmpty()) p0.toString() else "0"     // get the Tip

                    // call Tip  function so calculate the Tip amount
                    calculateTipAmountAndTotalBillAmount(saveInputAmount, viewTip, viewTotalBill)
                }
            })
        }

    }

    // calculate Tip amount and Total bill amount
    private fun calculateTipAmountAndTotalBillAmount(
        saveInputAmount: String,
        viewTip: TextView,
        viewTotalBill: TextView
    ) {

        // set view Tip amount
        var finalTipAmount =
            if (saveInputAmount != "0.00" || saveInputTip != "0.00") saveInputTip.toDouble() * (saveInputAmount.toDouble()) / 100 else 0.00
        viewTip.setText(if (finalTipAmount != 0.00) finalTipAmount.toString() else "$0.00")
        Log.i(tage, "calculate ${finalTipAmount}")
        // set view total bill amount
        var finalTotalBillAmount =
            if (saveInputAmount != "0.00") saveInputAmount.toDouble() + (saveInputTip.toDouble() * (saveInputAmount.toDouble()) / 100) else 0.00
        viewTotalBill.setText(if (finalTotalBillAmount != 0.00) finalTotalBillAmount.toString() else "$0.00")

    }

    fun tipPercentageButton(view: View) {
        when (view.id) {
            R.id.button1 -> editTextTip.setText("10")
            R.id.button2 -> editTextTip.setText("15")
            R.id.button3 -> editTextTip.setText("20")
        }
    }
}

