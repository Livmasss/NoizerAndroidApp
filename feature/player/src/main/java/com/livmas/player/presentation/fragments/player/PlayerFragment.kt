package com.livmas.player.presentation.fragments.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import com.bumptech.glide.Glide
import com.livmas.player.databinding.FragmentPlayerBinding
import com.livmas.ui.presentation.SharedViewModel
import com.livmas.ui.presentation.mappers.TrackModelMapper
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@UnstableApi
class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by viewModel<PlayerViewModel>()
    private val sharedViewModel by activityViewModel<SharedViewModel>()

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

    private fun setupObservers() {
        setupPlayedTrackObserver()
        setupTrackToPlayObserver()
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

    private fun setupTrackToPlayObserver() {
        sharedViewModel.trackToPlay.observe(viewLifecycleOwner) { dto ->
            dto?.let {
                viewModel.playTrack(TrackModelMapper.fromDTO(dto))
            }
        }
    }
}