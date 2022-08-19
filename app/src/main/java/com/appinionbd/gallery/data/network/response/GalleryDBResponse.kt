package com.appinionbd.gallery.data.network.response

import com.appinionbd.gallery.data.database.GalleryData

data class GalleryDBResponse(
    val data: GalleryData
):ArrayList<GalleryResponseItem>()