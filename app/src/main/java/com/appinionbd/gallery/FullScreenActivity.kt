package com.appinionbd.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.appinionbd.gallery.databinding.ActivityFullScreenBinding
import com.appinionbd.gallery.databinding.FragmentGalleryBinding
import com.bumptech.glide.Glide

class FullScreenActivity : AppCompatActivity() {

    private var binding: ActivityFullScreenBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_full_screen)

        val url = getIntent().getStringExtra("image_url")

        binding?.button?.setOnClickListener {
            finish()
        }

        binding?.myImage?.let {
            Glide.with(it)
                .load(url)
                .into(binding?.myImage!!)
        }


    }

    override fun onStop() {
        super.onStop()
        binding=null
    }
}