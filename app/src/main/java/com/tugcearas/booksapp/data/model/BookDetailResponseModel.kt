package com.tugcearas.booksapp.data.model

data class BookDetailResponseModel(
    val book: Book?,
    val message: String?,
    val success: Int?
)