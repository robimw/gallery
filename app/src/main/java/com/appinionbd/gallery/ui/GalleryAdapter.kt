package com.appinionbd.gallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appinionbd.gallery.R
import com.appinionbd.gallery.data.network.response.GalleryResponseItem
import com.appinionbd.gallery.databinding.GalleryItemBinding
import com.appinionbd.gallery.util.DiffCallback


class GalleryAdapter :
    PagingDataAdapter<GalleryResponseItem, GalleryAdapter.PagingGalleryHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  PagingGalleryHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.gallery_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PagingGalleryHolder, position: Int) {
        holder.galleryItemBinding.photos= getItem(position)
    }


    inner class PagingGalleryHolder(
        val galleryItemBinding: GalleryItemBinding
    ):RecyclerView.ViewHolder(galleryItemBinding.root)


}