package com.example.vize

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Sayaç TextViewi tanıt
        val countdownTimer: TextView = findViewById(R.id.countdown_timer)

        // Geri sayımı başlat (5000ms = 5 saniye)
        object : CountDownTimer(5000, 1000) {

            override fun onTick(kalan: Long) {
                // Kalan süreyi saniye cinsinden göster
                countdownTimer.text = (kalan / 1000).toString()
            }

            override fun onFinish() {
                // Sayaç bitince ana sayfaya geç
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}
