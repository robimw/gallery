package com.appinionbd.gallery.util

import androidx.recyclerview.widget.DiffUtil
import com.appinionbd.gallery.data.database.GalleryData

class DiffCallback : DiffUtil.ItemCallback<GalleryData>() {
    override fun areItemsTheSame(oldItem: GalleryData, newItem: GalleryData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GalleryData, newItem: GalleryData): Boolean {
        return oldItem.urls.small_s3 == newItem.urls.small_s3
    }
}