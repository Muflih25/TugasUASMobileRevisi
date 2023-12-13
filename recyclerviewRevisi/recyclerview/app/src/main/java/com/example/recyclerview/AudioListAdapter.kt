package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioListAdapter(
    private val audioList: List<Int>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AudioListAdapter.AudioViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(audioResId: Int)
    }

    inner class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val btnPlay: ImageButton = itemView.findViewById(R.id.btnPlay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audio, parent, false)
        return AudioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioResId = audioList[position]
        holder.textTitle.text = "Audio ${position + 1}"

        holder.btnPlay.setOnClickListener {
            listener.onItemClick(audioResId)
        }
    }

    override fun getItemCount(): Int {
        return audioList.size
    }
}
