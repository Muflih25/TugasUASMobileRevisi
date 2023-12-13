// LoginActivity.kt
package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Validasi login dengan memeriksa SharedPreferences
            if (isValidLogin(email, password)) {
                // Jika otentikasi berhasil, simpan status login ke file
                saveLoginStatus()
                Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show()

                // Lanjutkan ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika otentikasi gagal, tampilkan pesan kesalahan
                Toast.makeText(this, "Email Atau Password Yang Anda Masukkan Salah.", Toast.LENGTH_SHORT).show()
            }
        }

        // Tambahkan onClickListener untuk tombol Register
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegistrasiActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidLogin(email: String, password: String): Boolean {
        // Mendapatkan informasi akun yang terdaftar dari SharedPreferences
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val registeredEmail = sharedPreferences.getString("email", "")
        val registeredPassword = sharedPreferences.getString("password", "")

        // Memeriksa apakah email dan password cocok dengan yang terdaftar
        return email == registeredEmail && password == registeredPassword
    }

    private fun saveLoginStatus() {
        // Simpan status login ke file
        try {
            val file = "login_status.txt"
            val fileOutputStream = openFileOutput(file, MODE_PRIVATE)
            fileOutputStream.write("logged_in".toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}