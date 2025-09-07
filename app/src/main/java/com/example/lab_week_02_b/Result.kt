package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Result : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "com.example.lab_week_02_b.COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessageTextView = findViewById<TextView>(R.id.color_code_result_message)

        val colorCode = intent.getStringExtra(COLOR_KEY)

        if (colorCode != null && colorCode.isNotBlank()) {
            try {
                val colorToParse = if (colorCode.startsWith("#")) colorCode else "#$colorCode"
                backgroundScreen.setBackgroundColor(Color.parseColor(colorToParse))
                resultMessageTextView.text = getString(R.string.color_code_result_message, colorCode.uppercase())
            } catch (e: IllegalArgumentException) {
                Log.e("Result", "Invalid color code format: $colorCode", e)
                Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
                backgroundScreen.setBackgroundColor(Color.LTGRAY)
                resultMessageTextView.text = getString(R.string.color_code_input_invalid)
            } catch (e: Exception) {
                Log.e("Result", "Error processing color: $colorCode", e)
                Toast.makeText(this, getString(R.string.error_processing_color_toast), Toast.LENGTH_LONG).show()
                backgroundScreen.setBackgroundColor(Color.DKGRAY)
                resultMessageTextView.text = getString(R.string.error_processing_color_message)
            }
        } else {
            Log.w("Result", "No color code received from Intent.")
            Toast.makeText(this, getString(R.string.info_no_color_code_received_toast), Toast.LENGTH_LONG).show()
            backgroundScreen.setBackgroundColor(Color.WHITE)
            resultMessageTextView.text = getString(R.string.info_no_color_code_received_message)
        }
    }
}
