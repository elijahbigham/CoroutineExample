package com.example.threadexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {


    val timerTextView: TextView by lazy {
        findViewById(R.id.timerTextView)
    }
    val startButton: Button by lazy {
        findViewById(R.id.startButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coScope = CoroutineScope(Job() + Dispatchers.Default)
        startButton.setOnClickListener(){
            coScope.launch {
                countdownTimer()
            }
        }

    }
    suspend fun countdownTimer(){
        repeat(100) {
            (100-it).toString().run {
                withContext(Dispatchers.Main) {
                    timerTextView.text = this@run
                }
            }
            delay(1000)
        }
    }
}