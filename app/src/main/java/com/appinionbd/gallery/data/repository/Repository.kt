package com.appinionbd.gallery.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.appinionbd.gallery.data.database.GalleryDao
import com.appinionbd.gallery.data.database.RoomDB
import com.appinionbd.gallery.data.network.PhotoApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: PhotoApi,
    private val gallerDao:GalleryDao
){
    @OptIn(ExperimentalPagingApi::class)
    fun fetchRepos() = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false, prefetchDistance = 2),
        remoteMediator = GalleryMediator(api,gallerDao),
        pagingSourceFactory = {gallerDao.getData()}
    ).liveData

}