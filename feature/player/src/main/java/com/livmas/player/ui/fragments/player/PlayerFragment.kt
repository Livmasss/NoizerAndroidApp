package com.livmas.player.ui.fragments.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.util.UnstableApi
import com.livmas.player.databinding.FragmentPlayerBinding
import com.livmas.player.domain.MusicPlayer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@UnstableApi
class PlayerFragment : Fragment() {
    private val player: MusicPlayer by inject()
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

        setupPlayerView()
        playFonk()
    }

    private fun setupPlayerView() {
        binding.playerView.player = player.exoPlayer
    }
    private fun playFonk() {
        // Create a HLS media source pointing to a playlist uri.
        val item = viewModel.getSongByUri("http://pro13.easy4.team/segments/output.m3u8")
        player.playItemTracks(listOf(item))
    }
}