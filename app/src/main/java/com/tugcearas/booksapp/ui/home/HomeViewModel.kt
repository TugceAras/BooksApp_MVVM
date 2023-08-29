package com.tugcearas.booksapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugcearas.booksapp.data.model.Book
import com.tugcearas.booksapp.data.repo.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bookRepo : BookRepository): ViewModel() {

    private var _bookLiveData = MutableLiveData<List<Book>?>()
    val bookLiveData: LiveData<List<Book>?>
        get() = _bookLiveData

    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        _bookLiveData = bookRepo.bookLiveData
        _errorMessageLiveData = bookRepo.errorMessageLiveData
        _loadingLiveData = bookRepo.loadingLiveData
    }

    fun getBooks() {
        bookRepo.getAllBooks()
    }
}