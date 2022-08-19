package com.appinionbd.gallery.data.network

import com.appinionbd.gallery.data.database.GalleryData
import com.appinionbd.gallery.data.network.response.GalleryResponse
import com.appinionbd.gallery.data.network.response.GalleryResponseItem
import retrofit2.Response
import retrofit2.http.*


interface PhotoApi
{
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page:Int?
    ): ArrayList<GalleryData>

}