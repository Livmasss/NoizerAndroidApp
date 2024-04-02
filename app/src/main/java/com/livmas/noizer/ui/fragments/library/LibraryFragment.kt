package com.livmas.noizer.ui.fragments.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.livmas.noizer.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private val viewModel: LibraryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}