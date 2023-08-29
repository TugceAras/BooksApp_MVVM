package com.tugcearas.booksapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tugcearas.booksapp.databinding.FragmentDetailScreenBinding
import com.tugcearas.booksapp.util.extensions.gone
import com.tugcearas.booksapp.util.extensions.loadImage
import com.tugcearas.booksapp.util.extensions.toastMessage
import com.tugcearas.booksapp.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private lateinit var binding:FragmentDetailScreenBinding
    private val args : DetailScreenArgs by navArgs()
    private val bookDetailViewModel : DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickBackButton()
        bookDetailViewModel.getBookDetail(args.bookId)
        observeData()
    }

    private fun observeData() = with(binding){

        bookDetailViewModel.bookDetailLiveData.observe(viewLifecycleOwner) { book ->
            if(book != null){
                detailImageview.loadImage(book.imageUrl)
                tvDetailBookName.text = book.name
                tvAuthorName.text = book.author
                tvPublisherName.text = book.publisher
                tvPrice.text = "${book.price} ₺"

                if (book.isBestSeller == true) tvSellerCardview.visible()
                else tvSellerCardview.gone()
            }
            else{
                requireContext().toastMessage("Book not found!")
            }
        }

        bookDetailViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            requireContext().toastMessage(it)
        }
    }

    private fun clickBackButton(){
        binding.detailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}