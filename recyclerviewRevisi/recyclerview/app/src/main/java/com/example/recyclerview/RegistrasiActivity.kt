// RegistrasiActivity.kt
package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegistrasiActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextEmail = findViewById(R.id.editTextRegisterEmail)
        editTextPassword = findViewById(R.id.editTextRegisterPassword)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Contoh: Validasi email dan password (Anda dapat menggantinya dengan aturan validasi yang sesuai)
            if (isValid(email, password)) {
                // Simpan informasi registrasi ke file atau database (sesuai kebutuhan aplikasi Anda)
                saveRegistrationInfo(email, password)

                // Tampilkan pesan sukses dan arahkan ke halaman login
                Toast.makeText(this, "Registration successful. Please login.", Toast.LENGTH_SHORT)
                    .show()
                redirectToLogin()
            } else {
                // Tampilkan pesan kesalahan jika validasi gagal
                Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValid(email: String, password: String): Boolean {
        // Implementasi validasi email dan password sesuai kebutuhan.
        // Anda dapat menggunakan teknik validasi seperti Regex atau metode lainnya.
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun saveRegistrationInfo(email: String, password: String) {
        // Simpan informasi registrasi ke Shared Preferences
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun redirectToLogin() {
        // Kembali ke halaman login setelah registrasi berhasil
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}