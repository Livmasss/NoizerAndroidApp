package com.livmas.player.presentation.fragments.player

import android.content.ComponentName
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.bumptech.glide.Glide
import com.google.common.util.concurrent.MoreExecutors
import com.livmas.player.databinding.FragmentPlayerBinding
import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.presentation.models.TrackModel
import com.livmas.player.presentation.services.MusicPlaybackService
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@UnstableApi
class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by viewModel<PlayerViewModel>()
    private val getTrackURLUseCase: GetTrackURLUseCase by inject()
    private val getMediaItemUseCase: GetMediaItemUseCase by inject()

    override fun onStart() {
        super.onStart()

        val sessionToken = SessionToken(requireContext(), ComponentName(requireContext(), MusicPlaybackService::class.java))
        val controllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
        controllerFuture.addListener(
            {
                CoroutineScope(Dispatchers.IO).launch {
                    val url = getTrackURLUseCase.execute(0)
                    val item = getMediaItemUseCase.execute(url)
                    CoroutineScope(Dispatchers.Main).launch {
                        controllerFuture.get().setMediaItem(item)
                        controllerFuture.get().prepare()
                        controllerFuture.get().playWhenReady = true
                    }
                }
            },
            MoreExecutors.directExecutor()
        )
    }

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