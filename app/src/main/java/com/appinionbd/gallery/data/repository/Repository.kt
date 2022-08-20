package com.appinionbd.gallery.data.repository

import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.room.RoomDatabase
import com.appinionbd.gallery.data.database.GalleryDao
import com.appinionbd.gallery.data.database.RoomDB
import com.appinionbd.gallery.data.network.PhotoApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: PhotoApi,
    private val database: RoomDB
){
    @OptIn(ExperimentalPagingApi::class)
    fun fetchRepos() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        remoteMediator = GalleryMediator(api,database),
        pagingSourceFactory = {database.galleryDao().getData()}
    ).liveData

}