package com.appinionbd.gallery

import android.graphics.Bitmap
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

@BindingAdapter("imageBitmap")
fun showBitmap(imageview:ImageView,img:Bitmap)
{
    imageview.setImageBitmap(img)
}


fun View.toast(message:String)
{
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}