package com.example.guessinggame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guessinggame.ui.theme.GuessingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        val modeBeginner = findViewById<Button>(R.id.modeBeginner)
        val modeExpert = findViewById<Button>(R.id.modeExpert)
        modeBeginner.setOnClickListener {
            val intent = Intent(this, BeginnerActivity::class.java)
            startActivity(intent)
        }
        modeExpert.setOnClickListener {
            val intent = Intent(this, ExpertActivity::class.java)
            startActivity(intent)
        }
    }
}
