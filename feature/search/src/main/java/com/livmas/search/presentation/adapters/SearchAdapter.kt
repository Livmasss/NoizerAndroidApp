package com.livmas.search.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.search.presentation.models.TrackModel
import com.livmas.ui.databinding.TrackItemLayoutBinding

internal class SearchAdapter(var data: List<TrackModel>): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    inner class SearchHolder(private val binding: TrackItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TrackModel) {
            binding.tvTrackAuthor.text = model.author
            binding.tvTrackTitle.text = model.title
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
        holder.bind(data[position])
    }

    fun updateContent(list: List<TrackModel>) {
        data = list
    }
}