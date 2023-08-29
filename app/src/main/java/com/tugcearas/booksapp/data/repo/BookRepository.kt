package com.tugcearas.booksapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tugcearas.booksapp.data.model.Book
import com.tugcearas.booksapp.data.model.BookDetailResponseModel
import com.tugcearas.booksapp.data.model.BookResponseModel
import com.tugcearas.booksapp.data.source.BookApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api : BookApi
) {
    val bookLiveData = MutableLiveData<List<Book>?>()
    val bookDetailLiveData = MutableLiveData<Book?>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun getAllBooks(){
        loadingLiveData.value = true
        api.getAllBooks().enqueue(object : Callback<BookResponseModel>{
            override fun onResponse(call: Call<BookResponseModel>, response: Response<BookResponseModel>) {

                val result = response.body()?.books

                if (result.isNullOrEmpty().not()) {
                    bookLiveData.value = result
                } else {
                    bookLiveData.value = null
                }

                loadingLiveData.value = false
            }

            override fun onFailure(call: Call<BookResponseModel>, t: Throwable) {
                errorMessageLiveData.value = t.message.orEmpty()
                loadingLiveData.value = false
                Log.e("GetAllBooks", t.message.orEmpty())
            }
        })
    }

    fun getDetailBook(id:Int){
        api.getDetailBook(id).enqueue(object : Callback<BookDetailResponseModel> {
            override fun onResponse(call: Call<BookDetailResponseModel>, response: Response<BookDetailResponseModel>) {

                bookDetailLiveData.value = response.body()?.book
            }

            override fun onFailure(call: Call<BookDetailResponseModel>, t: Throwable) {
                errorMessageLiveData.value = t.message.orEmpty()
                Log.e("GetBookDetail", t.message.orEmpty())
            }
        })
    }
}