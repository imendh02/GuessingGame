package com.example.guessinggame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class ExpertActivity : ComponentActivity() {
    private var randomInt = Random.nextInt(1, 101)
    var countDownTimerRunning = false
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expert_page)
        println("random: $randomInt")
        val editText = findViewById<EditText>(R.id.number)
        val time = findViewById<TextView>(R.id.time)
        val text = findViewById<TextView>(R.id.text)
        val result = findViewById<Button>(R.id.result)
        val reset = findViewById<Button>(R.id.reset)
        val start = findViewById<Button>(R.id.start)
        result.isEnabled = false
        result.alpha = 0.5f

        start.setOnClickListener {
            result.isEnabled = true
            result.alpha = 1f
            start.isEnabled = false
            start.alpha = 0.5f
            val millisInFuture: Long = 60000
            val countDownInterval: Long = 1000
            countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    countDownTimerRunning = true
                    val seconds = (millisUntilFinished / 1000).toInt()
                    val minutes = seconds / 60
                    val remainingSeconds = seconds % 60
                    val timeLeftFormatted = String.format("%02d:%02d", minutes, remainingSeconds)
                    time.text = timeLeftFormatted
                }

                override fun onFinish() {
                    countDownTimerRunning = false
                    time.text = "00:00"
                    text.text = "Time's up! The number was $randomInt."
                }
            }.start()
        }
        result.setOnClickListener {
            if (countDownTimerRunning) {
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
                        text.text = "Congratulations! You have guessed the number in ${time.text} seconds."
                        countDownTimer.cancel()
                    }
                } else {
                    text.text = "Please enter a valid number."
                }
            } else {
                text.text = "Time's up! The number was $randomInt."
            }
        }
        reset.setOnClickListener {
            randomInt = Random.nextInt(1, 101)
            println("random: $randomInt")
            editText.setText("")
            text.text=""
            time.text="01:00"
            result.isEnabled = false
            result.alpha = 0.5f
            start.isEnabled = true
            start.alpha = 1f
            countDownTimer.cancel()
        }
    }
}