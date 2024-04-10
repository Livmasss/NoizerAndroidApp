package com.livmas.player.ui.fragments.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import com.bumptech.glide.Glide
import com.livmas.player.databinding.FragmentPlayerBinding
import com.livmas.player.ui.models.TrackModel
import com.livmas.util.domain.models.TrackDTO
import org.koin.androidx.viewmodel.ext.android.viewModel

@UnstableApi
class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by viewModel<PlayerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        playPhonk()
    }

    private fun setupViews() {
        setupPlayerView()
        setupLikeToggleButton()
    }
    private fun setupPlayerView() {
        viewModel.setupPlayerForView(binding.playerView)
    }
    private fun setupLikeToggleButton() {
        binding.tbLike.setOnClickListener {
            viewModel.changePlayedTrackLikedState()
        }
    }

    private fun playPhonk() {
        // Create a HLS media source pointing to a playlist uri.
        viewModel.playTrack(
            TrackModel(0, "Лютый фонк", "Бетховен",
                "https://t2.genius.com/unsafe/504x504/https%3A%2F%2Fimages.genius.com%2Fe4833b496aab74f8f208e91bde50dbd5.1000x1000x1.png",
                false)
        )
    }

    private fun setupObservers() {
        setupPlayedTrackObserver()
    }

    private fun setupPlayedTrackObserver() {
        viewModel.playedTrack.observe(viewLifecycleOwner) {
            binding.tvTrackTitle.text = it.title
            binding.tvTrackAuthor.text = it.author
            Glide.with(requireContext())
                .load(it.coverUrl)
                .into(binding.ivTrackCover)
            binding.tbLike.isChecked = it.likedState
        }
    }
}