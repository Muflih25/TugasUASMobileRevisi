package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pemeriksaan apakah pengguna sudah masuk atau belum
        if (isUserLoggedIn()) {
            setContentView(R.layout.activity_main)
            recyclerView = findViewById(R.id.rv_video)
            recyclerView.setHasFixedSize(true)
            list.addAll(getList())
            showRecyclerList()

            // Tambahkan tombol log out
            val buttonLogout: Button = findViewById(R.id.buttonLogout)
            buttonLogout.setOnClickListener {
                performLogout()
            }

            // Tambahkan listener untuk menu audio
            val audioMenu: TextView = findViewById(R.id.audioMenu)
            audioMenu.setOnClickListener {
                val intent = Intent(this, AudioActivity::class.java)
                startActivity(intent)
            }

        } else {
            // Jika pengguna belum masuk, arahkan ke LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        // Pengecekan apakah pengguna sudah masuk atau belum menggunakan file
        try {
            openFileInput("login_status.txt").use {
                val reader = BufferedReader(InputStreamReader(it))
                val status = reader.readLine()
                return status == "logged_in"
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    private fun getList(): ArrayList<video> {
        // Implementasi mendapatkan daftar video
        val gambar = resources.obtainTypedArray(R.array.data_gambar)
        val dataName = resources.getStringArray(R.array.judul_video)
        val dataDescription = resources.getStringArray(R.array.data_dekripsi)
        val videoId = resources.obtainTypedArray(R.array.video_id)
        val listvideo = ArrayList<video>()
        for (i in dataName.indices) {
            val video = video(
                gambar.getResourceId(i, -1),
                dataName[i],
                dataDescription[i],
                videoId.getResourceId(i, -1)
            )
            listvideo.add(video)
        }
        return listvideo
    }

    private fun showRecyclerList() {
        // Implementasi menampilkan daftar video
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listadapter = ListAdapter(list)
        recyclerView.adapter = listadapter
    }

    private fun performLogout() {
        // Hapus status login dari file
        try {
            val file = "login_status.txt"
            deleteFile(file)
            // Kembalikan ke halaman login setelah logout
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
