package com.appinionbd.gallery

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import okhttp3.internal.wait

@BindingAdapter("imageUrl")
fun loadImage(imageview:ImageView,url:String?)
{

    val circularProgressDrawable = CircularProgressDrawable(imageview.context).apply {
        strokeWidth = 5f
        centerRadius = 25f
    }
    circularProgressDrawable.start()

    Glide.with(imageview.context)
        .load(url)
        .dontAnimate()
            //color placeholder may produce
        .placeholder(circularProgressDrawable)
        .into(imageview)
}


fun View.toast(message:String)
{
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}