package com.tugcearas.booksapp.util.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun Context.toastMessage(message:String) =
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
