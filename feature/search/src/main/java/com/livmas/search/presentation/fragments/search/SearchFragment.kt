package com.livmas.search.presentation.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.search.databinding.FragmentSearchBinding
import com.livmas.search.presentation.adapters.SearchAdapter
import com.livmas.ui.presentation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()
    private val sharedViewModel by activityViewModel<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        initiateData()
    }

    private fun initiateData() =
        viewModel.initiateTracks()

    private fun setupViews() {
        setupSearchBarView()
        setupSearchRecyclerView()
    }

    private fun setupSearchRecyclerView() {
        adapter = SearchAdapter(
            requireContext(),
            listOf(),
        ) {
            sharedViewModel.trackToPlay.postValue(it)
            sharedViewModel.isPlayerVisible.postValue(true)
        }

        binding.rvSearchResult.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchResult.adapter = adapter
    }
    private fun setupSearchBarView() {
        binding.etSearchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.findTracks(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                return
            }
        })
    }

    private fun setupObservers() {
        setupSearchResultObserver()
    }
    private fun setupSearchResultObserver() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.updateContent(it)
            adapter.notifyDataSetChanged()
        }
    }
}