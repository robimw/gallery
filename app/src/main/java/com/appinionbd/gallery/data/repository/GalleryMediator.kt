package com.appinionbd.gallery.data.repository


import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.appinionbd.gallery.data.database.GalleryDao
import com.appinionbd.gallery.data.database.GalleryData
import com.appinionbd.gallery.data.database.GalleryPageData
import com.appinionbd.gallery.data.network.PhotoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalPagingApi::class)
class GalleryMediator(
    private val api: PhotoApi,
    private val galleryDao:GalleryDao
): RemoteMediator<Int, GalleryData>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GalleryData>
    ): MediatorResult {

        return try {

             when(loadType)
            {
                LoadType.REFRESH -> loadData()
                LoadType.PREPEND -> Unit
                LoadType.APPEND ->
                {
                    state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    loadData()
                }
            }

            MediatorResult.Success(endOfPaginationReached = true)

        }catch (e:Exception)
        {
           // Log.e("error",e.toString())
            MediatorResult.Error(e)
        }
    }

    private suspend fun getKey(): GalleryPageData?
    {
        return galleryDao.getPage()

    }

    private suspend fun loadData()
    {
        val loadpage=  getKey()

        val page = loadpage?.next?:1

        val apicall = api.getPhotos(page)

        if(apicall.isNotEmpty()) {

            withContext(Dispatchers.IO)
            {

                galleryDao.insert(apicall)
                galleryDao.insertLastPage(GalleryPageData(0,page.plus(1),0))

            }
        }

        Log.e("page",page.toString())

    }

}

