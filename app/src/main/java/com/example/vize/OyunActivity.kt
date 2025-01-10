package com.example.vize

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class OyunActivity : AppCompatActivity() {
    //puan ve rastgele seçilen meyve değişkenleri
    private var puan = 0 // Başlangıç puanı
    private lateinit var secilenMeyve: String // Doğru cevabı saklayacak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun)

        // View öğelerini tanımlama
        val karisikMeyveText = findViewById<TextView>(R.id.tvKarisikMeyve)
        val tahminInput = findViewById<EditText>(R.id.etTahmin)
        val kontrolButton = findViewById<Button>(R.id.btnKontrol)
        val puanText = findViewById<TextView>(R.id.tvPuan)

        // Meyveler listesi
        val meyvelerListesi = listOf("Elma", "Armut", "Çilek", "Kiraz", "Portakal", "Muz")

        // Rastgele bir meyve seç ve harflerini karıştır
        secilenMeyve = meyvelerListesi[Random.nextInt(meyvelerListesi.size)]
        val karisikMeyve = secilenMeyve.toCharArray().apply { shuffle() }.joinToString("")

        // Karışık meyve adını TextView'e yaz
        karisikMeyveText.text = karisikMeyve

        // Kontrol butonuna tıklama işlemi
        kontrolButton.setOnClickListener {
            val kullaniciTahmini = tahminInput.text.toString()

            if (kullaniciTahmini.equals(secilenMeyve, ignoreCase = true)) {//büyük küçük harf duyarlılığı yok
                // Doğru tahmin
                puan += 10
                Toast.makeText(this, "Doğru tahmin", Toast.LENGTH_SHORT).show()
                tahminInput.text.clear()

                // Yeni bir meyve seç
                secilenMeyve = meyvelerListesi[Random.nextInt(meyvelerListesi.size)] //random yeni bir meyveyi listeden seç
                val yeniKarisikMeyve = secilenMeyve.toCharArray().apply { shuffle() }.joinToString("")//secilenmeyve stringini karakter dizisine dönüştür,sonra karıştır,sonra birliştir.
                karisikMeyveText.text = yeniKarisikMeyve
            } else {
                // Yanlış tahmin
                puan = 0
                Toast.makeText(this, "Yanlış tahmin", Toast.LENGTH_SHORT).show()
                tahminInput.text.clear()
            }

            // Puanı güncelle
            puanText.text = "Puan: $puan"
        }
    }
}
