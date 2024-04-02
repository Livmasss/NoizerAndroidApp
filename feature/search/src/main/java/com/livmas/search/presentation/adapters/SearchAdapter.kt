package com.livmas.search.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livmas.search.presentation.mappers.TrackAdapterModelMapper
import com.livmas.ui.databinding.TrackItemLayoutBinding
import com.livmas.ui.presentation.models.TrackModel
import com.livmas.util.domain.models.TrackDTO

internal class SearchAdapter(
    private val context: Context,
    private var data: List<TrackAdapterModel>,
    private val onTrackSelectedListener : (track: TrackDTO) -> Unit
): RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    data class TrackAdapterModel (
        val id: Long,
        val title: String,
        val author: String,
        val coverUrl: String,
        val likedState: Boolean
    )


    inner class SearchHolder(private val binding: TrackItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, model: TrackAdapterModel) = binding.apply {
            tvTrackAuthor.text = model.author
            tvTrackTitle.text = model.title

            Glide.with(context)
                .load(model.coverUrl)
                .into(ivTrackCover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val viewBinding = TrackItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)

        return SearchHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(context, data[position])
        holder.itemView.setOnClickListener{
            onTrackSelectedListener(
                TrackAdapterModelMapper.toDTO(data[position])
            )
        }
    }

    fun updateContent(list: List<TrackModel>) {
        data = list.map {
            TrackAdapterModelMapper.fromPresentationModel(it)
        }
    }
}