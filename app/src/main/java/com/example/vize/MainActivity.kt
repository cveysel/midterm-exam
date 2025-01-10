package com.example.vize

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences


class MainActivity : AppCompatActivity() {
    //beni hatırla ksımının kalıcı tutulması için shared preferences
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "UserPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewleri tanıt
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etUsername2)
        val btnLogin = findViewById<Button>(R.id.button)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        // SharedPreferences başlat
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Kaydedilen kullanıcı adı ve şifreyi al
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val rememberMe = sharedPreferences.getBoolean("rememberMe", false)

        // Eğer Beni Hatırla önceden seçilmişse değerleri yükle
        if (rememberMe) {
            etUsername.setText(savedUsername)
            etPassword.setText(savedPassword)
            checkBox.isChecked = true
        } else {
            checkBox.isChecked = false
        }
        //giriş yap butonu
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Kullanıcı adı ve şifre kontrolü
            if (username == "Cemal" && password == "123") {
                val editor = sharedPreferences.edit()

                if (checkBox.isChecked) {
                    // "Beni Hatırla" seçiliyse, bilgileri kaydet
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.putBoolean("rememberMe", true)
                } else {
                    // Seçili değilse, kaydedilen bilgileri temizle
                    editor.clear()
                }
                editor.apply()

                // YemekSayfasi'na geç
                val intent = Intent(this, YemekSayfasi::class.java)
                startActivity(intent)
            }
            else {
                // Hatalı giriş mesajı
                Toast.makeText(this, "Hatalı Giriş", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
