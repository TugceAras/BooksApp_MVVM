package com.tugcearas.booksapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugcearas.booksapp.data.model.Book
import com.tugcearas.booksapp.data.repo.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookRepo: BookRepository
) :ViewModel() {

    private var _bookDetailLiveData = MutableLiveData<Book?>()
    val bookDetailLiveData: LiveData<Book?>
        get() = _bookDetailLiveData

    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    init {
        _bookDetailLiveData = bookRepo.bookDetailLiveData
        _errorMessageLiveData = bookRepo.errorMessageLiveData
    }

    fun getBookDetail(id: Int) {
        bookRepo.getDetailBook(id)
    }
}