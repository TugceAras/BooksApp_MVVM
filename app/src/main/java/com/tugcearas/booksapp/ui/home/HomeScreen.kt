package com.tugcearas.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.booksapp.databinding.FragmentHomeScreenBinding
import com.tugcearas.booksapp.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment(), HomeAdapter.BookListener {

    private lateinit var binding: FragmentHomeScreenBinding
    private val homeAdapter by lazy { HomeAdapter(this) }
    private val bookViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.adapter = homeAdapter
        bookViewModel.getBooks()
        observeData()
    }

    private fun observeData(){
        bookViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        bookViewModel.bookLiveData.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                homeAdapter.submitList(list)
            } else {
                requireContext().toastMessage("Empty List!")
            }
        }

        bookViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            requireContext().toastMessage(it)
        }
    }

    override fun clickBookItem(id: Int) {
        val action = HomeScreenDirections.actionHomeScreenToDetailScreen(bookId = id)
        findNavController().navigate(action)
    }
}