package com.appinionbd.gallery.data.network

import com.appinionbd.gallery.data.database.GalleryData
import retrofit2.http.*


interface PhotoApi
{
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page:Int?
    ): ArrayList<GalleryData>

}