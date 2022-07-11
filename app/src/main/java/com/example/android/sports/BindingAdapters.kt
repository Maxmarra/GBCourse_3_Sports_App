package com.example.android.sports

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imageResourceId: Int) {
        imgView.load(imageResourceId){
        }
}


