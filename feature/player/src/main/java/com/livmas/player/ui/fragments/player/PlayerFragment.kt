package com.livmas.player.ui.fragments.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.livmas.player.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {
    private lateinit var player: ExoPlayer
    private lateinit var binding: FragmentPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @UnstableApi override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate the player.
        player = ExoPlayer.Builder(requireContext())
            .build()
        binding.playerView.player = player

        // Create a HLS media source pointing to a playlist uri.
        val source = HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri("https://cdn.bitmovin.com/content/assets/art-of-motion-dash-hls-progressive/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa-audio-only.m3u8"))

        // Set the media item to be played.
        player.setMediaSource(source)
        // Prepare the player.
        player.prepare()
    }
}