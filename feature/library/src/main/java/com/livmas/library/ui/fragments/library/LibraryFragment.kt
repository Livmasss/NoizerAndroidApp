package com.livmas.library.ui.fragments.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.livmas.library.databinding.LibraryFragmentBinding

class LibraryFragment : Fragment() {

    private lateinit var binding: LibraryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}