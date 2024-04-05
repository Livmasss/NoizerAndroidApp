package com.livmas.player.ui.fragments.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.livmas.player.databinding.FragmentPlayerBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

@UnstableApi class PlayerFragment : Fragment() {
    private lateinit var player: ExoPlayer
    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by activityViewModel<PlayerViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPlayer()
        // Create a HLS media source pointing to a playlist uri.
        val item = viewModel.getSongByUri("http://pro13.easy4.team/segments/output.m3u8")

        // Set the media item to be played.
        player.setMediaItem(item)
        // Prepare the player.
        player.prepare()
        player.playWhenReady = true
    }

    private fun setupPlayer() {
        // Instantiate the player.
        player = ExoPlayer.Builder(requireContext())
            .build()
        binding.playerView.player = player
    }
}