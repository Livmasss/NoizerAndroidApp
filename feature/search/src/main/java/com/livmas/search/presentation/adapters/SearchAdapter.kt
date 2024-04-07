package com.livmas.search.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livmas.ui.databinding.TrackItemLayoutBinding

internal class SearchAdapter(private val context: Context, var data: List<TrackModel>): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    data class TrackModel (
        val id: Long,
        val title: String,
        val author: String,
        val coverUrl: String
    )

    inner class SearchHolder(private val binding: TrackItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, model: TrackModel) = binding.apply {
            tvTrackAuthor.text = model.author
            tvTrackTitle.text = model.title

            Glide.with(context)
                .load(model.coverUrl)
                .into(ivTrackCover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            TrackItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(context, data[position])
    }

    fun updateContent(list: List<TrackModel>) {
        data = list
    }
}