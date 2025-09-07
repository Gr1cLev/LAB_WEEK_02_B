package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "com.example.lab_week_02_b.COLOR_KEY"
        const val ERROR_KEY = "com.example.lab_week_02_b.ERROR_KEY"
    }

    private lateinit var launcher: ActivityResultLauncher<Intent>

    private val submitButton: Button
        get() = findViewById(R.id.submit_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Register launcher
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val errorMsg = result.data?.getStringExtra(ERROR_KEY)
                if (!errorMsg.isNullOrEmpty()) {
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        }

        submitButton.setOnClickListener {
            val colorCode = findViewById<TextInputEditText>(R.id.color_code_input_field).text.toString()
            if (colorCode.isNotEmpty()) {
                if (colorCode.length < 6) {
                    Toast.makeText(this, getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG).show()
                } else {
                    val resultIntent = Intent(this, Result::class.java)
                    resultIntent.putExtra(COLOR_KEY, colorCode)
                    launcher.launch(resultIntent)
                }
            } else {
                Toast.makeText(this, getString(R.string.color_code_input_empty), Toast.LENGTH_LONG).show()
            }
        }
    }
}
