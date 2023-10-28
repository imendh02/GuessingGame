package com.example.guessinggame;

import android.os.Bundle;
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity;
import kotlin.random.Random

class BeginnerActivity : ComponentActivity() {
    private var randomInt = Random.nextInt(1, 101)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beginner_page)
        println("random: $randomInt")
        val editText = findViewById<EditText>(R.id.number)
        val result = findViewById<Button>(R.id.result)
        val reset = findViewById<Button>(R.id.reset)
        val text = findViewById<TextView>(R.id.text)
        val history = mutableListOf<Int>()
        val historyTextView = findViewById<TextView>(R.id.historyText)

        result.setOnClickListener {
            val number = editText.text.toString().toIntOrNull()
            if (number != null) {
                if (number < randomInt) {
                    // Handle the case when the number is smaller
                    text.text = "The number is smaller."
                } else if (number > randomInt) {
                    // Handle the case when the number is larger
                    text.text = "The number is larger."
                } else {
                    // Handle the case when the number is equal
                    text.text = "Congratulations! You have guessed the number."
                }
                history.add(number)
                historyTextView.text=""
                history.forEachIndexed { index, value ->
                    historyTextView.append("Attempt ${index + 1}: $value\n")
                }
            } else {
                text.text = "Please enter a valid number."
            }
        }
        reset.setOnClickListener {
            randomInt = Random.nextInt(1, 101)
            println("random: $randomInt")
            editText.setText("")
            text.text=""
            history.clear()
            historyTextView.text=""
        }
    }
}