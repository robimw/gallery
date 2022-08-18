package com.appinionbd.gallery

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(imageview:ImageView,url:String)
{
    Glide.with(imageview)
        .load(url)
        .into(imageview)
}

fun View.toast(message:String)
{
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}