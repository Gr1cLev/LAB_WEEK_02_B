package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "com.example.lab_week_02_b.COLOR_KEY"
    }
    private val submitButton: Button
        get() = findViewById(R.id.submit_button)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton.setOnClickListener{
            val colorCode =
                findViewById<TextInputEditText>(R.id.color_code_input_field).text.toString()
            if(colorCode.isNotEmpty()){
                if (colorCode.length < 6){
                    Toast
                        .makeText(this,
                            getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG)
                        .show()
                }
                else{
                    val ResultIntent = Intent(this, Result::class.java)
                    ResultIntent.putExtra(COLOR_KEY, colorCode)
                    startActivity(ResultIntent)
                }
            }
            else{
                Toast
                    .makeText(this, getString(R.string.color_code_input_empty),
                        Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}

