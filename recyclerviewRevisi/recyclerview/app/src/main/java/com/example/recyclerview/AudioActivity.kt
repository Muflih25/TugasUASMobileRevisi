package com.example.recyclerview

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AudioActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var recyclerView: RecyclerView
    private var isAudioPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        recyclerView = findViewById(R.id.recyclerView)

        // Tambahkan ID audio baru ke dalam daftar
        val audioList = listOf(
            R.raw.audio01,
            R.raw.audio02,
            R.raw.audio03,
            R.raw.audio04,
            R.raw.audio05,
            R.raw.audio06,
            R.raw.audio07,
            R.raw.audio08,
            R.raw.audio09,
            R.raw.audio10,
            R.raw.audio11,
            R.raw.audio12,
            R.raw.audio13,
            R.raw.audio14
        )

        val adapter = AudioListAdapter(audioList, object : AudioListAdapter.OnItemClickListener {
            override fun onItemClick(audioResId: Int) {
                // Memutar atau menghentikan pemutaran audio saat item di RecyclerView diklik
                if (isAudioPlaying) {
                    stopAudio()
                } else {
                    startAudio(audioResId)
                }
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun startAudio(audioResId: Int) {
        mediaPlayer = MediaPlayer.create(this, audioResId)
        mediaPlayer?.start()
        isAudioPlaying = true
    }

    private fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isAudioPlaying = false
    }

    override fun onStop() {
        super.onStop()
        if (isAudioPlaying) {
            stopAudio()
        }
    }
}
