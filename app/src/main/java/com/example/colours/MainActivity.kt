package com.example.colours

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etRedChannel: EditText
    private lateinit var etGreenChannel: EditText
    private lateinit var etBlueChannel: EditText
    private lateinit var getButton: Button
    private lateinit var resetButton: Button
    private lateinit var colorOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etRedChannel = findViewById(R.id.et_redChannel)
        etGreenChannel = findViewById(R.id.et_greenChannel)
        etBlueChannel = findViewById(R.id.et_blueChannel)
        getButton = findViewById(R.id.get_button)
        resetButton = findViewById(R.id.reset_button)
        colorOutput = findViewById(R.id.colourOutput)

        getButton.setOnClickListener {

            hideKeyboard(this)

            if (etRedChannel.text.length != 2 || etGreenChannel.text.length != 2 || etBlueChannel.text.length != 2) {
                Toast.makeText(this, "Missing input!", Toast.LENGTH_SHORT).show()
            } else {
                val colourString = "#${etRedChannel.text}${etGreenChannel.text}${etBlueChannel.text}"

                val colour = Color.parseColor(colourString)
                colorOutput.text = colourString
                colorOutput.setBackgroundColor(colour)

                reset()
            }
        }

        resetButton.setOnClickListener {
            hideKeyboard(this)
            colorOutput.text = getString(R.string.tv_colourOutput_text)
            colorOutput.setBackgroundColor(Color.parseColor("#FFFFFF"))
            reset()
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(activity.currentFocus!!.applicationWindowToken, 0)
    }

    private fun reset() {
        etRedChannel.text.clear()
        etGreenChannel.text.clear()
        etBlueChannel.text.clear()

        etRedChannel.clearFocus()
        etGreenChannel.clearFocus()
        etBlueChannel.clearFocus()
    }
}