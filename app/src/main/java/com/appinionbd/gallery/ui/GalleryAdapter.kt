package com.appinionbd.gallery.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appinionbd.gallery.FullScreenActivity
import com.appinionbd.gallery.R
import com.appinionbd.gallery.data.database.GalleryData
import com.appinionbd.gallery.databinding.GalleryItemBinding
import com.appinionbd.gallery.util.DiffCallback


class GalleryAdapter :
    PagingDataAdapter<GalleryData, GalleryAdapter.PagingGalleryHolder>(DiffCallback()) {

    private var mcontext:Context ? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingGalleryHolder{

            mcontext=parent.context

             return PagingGalleryHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.gallery_item,
                    parent,
                    false
                )
            )

        }

    override fun onBindViewHolder(holder: PagingGalleryHolder, position: Int) {
        holder.galleryItemBinding.photos= getItem(position)

        holder.galleryItemBinding.ivItem.setOnClickListener {
            mcontext?.let {

                val  intent = Intent(mcontext, FullScreenActivity::class.java)
                intent.putExtra("image_url", getItem(position)?.urls?.small_s3);
                mcontext!!.startActivity(intent)
            }

        }
    }


    inner class PagingGalleryHolder(
        val galleryItemBinding: GalleryItemBinding
    ):RecyclerView.ViewHolder(galleryItemBinding.root)


}