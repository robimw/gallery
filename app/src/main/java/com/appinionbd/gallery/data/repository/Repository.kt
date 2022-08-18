package com.appinionbd.gallery.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.appinionbd.gallery.data.GallerySource
import com.appinionbd.gallery.data.network.PhotoApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: PhotoApi
){
    fun fetchRepos() = Pager(
        config = PagingConfig(pageSize = 10)
    ){GallerySource(api)}.liveData

}