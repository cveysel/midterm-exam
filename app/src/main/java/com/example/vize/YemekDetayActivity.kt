package com.example.vize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class YemekDetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_detay)
        //viewleri tanıt
        val yemekAdi = findViewById<TextView>(R.id.yemekAdi)
        val yemekTarifi = findViewById<TextView>(R.id.yemekTarifi)
        val yemekYapilisi = findViewById<TextView>(R.id.yemekYapilisi)

        // Intent ile gönderilen yemeğin bilgilerini al
        val ad = intent.getStringExtra("yemekAdi")
        val tarif = intent.getStringExtra("yemekTarifi")
        val yapilis = intent.getStringExtra("yemekYapilisi")

        // TextView'lere bilgileri atıyoruz
        yemekAdi.text = ad
        yemekTarifi.text = "Tarif: $tarif"
        yemekYapilisi.text = "Yapılışı: $yapilis"
    }
}
