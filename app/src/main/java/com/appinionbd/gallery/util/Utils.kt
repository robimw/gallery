package com.appinionbd.gallery

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(imageview:ImageView,url:String?)
{

    val circularProgressDrawable = CircularProgressDrawable(imageview.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()



    Glide.with(imageview)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(imageview)
}


fun View.toast(message:String)
{
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}