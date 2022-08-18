package com.appinionbd.gallery.util

import androidx.recyclerview.widget.DiffUtil
import com.appinionbd.gallery.data.network.response.GalleryResponseItem

class DiffCallback : DiffUtil.ItemCallback<GalleryResponseItem>() {
    override fun areItemsTheSame(oldItem: GalleryResponseItem, newItem: GalleryResponseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GalleryResponseItem, newItem: GalleryResponseItem): Boolean {
        return oldItem.id == newItem.id
    }
}