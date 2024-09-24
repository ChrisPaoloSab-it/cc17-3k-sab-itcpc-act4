package com.example.tipcalculator

import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var roundup: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button: Button = findViewById(R.id.calculatebutton)
        val tipResult = findViewById<TextView>(R.id.tipTotal)
        roundup = findViewById<Switch>(R.id.roundUp)

        button.setOnClickListener {
            val tip = tipCalculator()
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            tipResult.text = formattedTip
        }
    }
        private fun tipCalculator():Double {
            val cost = findViewById<EditText>(R.id.initialamount).text.toString().toDoubleOrNull() ?: 0.0
            val tipChoices = findViewById<RadioGroup>(R.id.tipChoices)
            val tipPercentage = when (tipChoices.checkedRadioButtonId) {
                R.id.amazing -> 0.20
                R.id.good -> 0.18
                R.id.ok -> 0.15
                else -> 0.0
            }
            if (roundup.isChecked) {
                return kotlin.math.ceil(cost * tipPercentage)
            }
            return cost * tipPercentage


    }
}