package com.livmas.noizer.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.livmas.noizer.R
import com.livmas.noizer.databinding.ActivityMainBinding
import com.livmas.ui.presentation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel by viewModel<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupObservers()
    }

    private fun setupNavController() {
        binding.navView.setupWithNavController(
            findNavController(R.id.nav_host_fragment_activity_main)
        )
    }

    private fun setupObservers() {
        setupPlayerVisibilityObserver()
    }
    private fun setupPlayerVisibilityObserver() {
        sharedViewModel.isPlayerVisible.observe(this) {
            binding.fcvPlayer.visibility = if (it)
                View.VISIBLE
            else
                View.GONE
        }
    }
}