package com.appinionbd.gallery.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.appinionbd.gallery.data.database.GalleryData
import com.appinionbd.gallery.data.database.GalleryPageData
import com.appinionbd.gallery.data.database.RoomDB
import com.appinionbd.gallery.data.network.PhotoApi
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class GalleryMediator @Inject constructor(
    private val api: PhotoApi,
    private val database: RoomDB
): RemoteMediator<Int, GalleryData>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GalleryData>
    ): MediatorResult {

        return try {

             when(loadType)
            {
                LoadType.REFRESH ->
                {
                    if(state.firstItemOrNull()!=null)
                       return MediatorResult.Success(
                            endOfPaginationReached = true)
                    else
                        Unit
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true)

                LoadType.APPEND ->
                {
                      state.firstItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                }
            }

            val loadpage = getKey()

            val page = loadpage?.next?:1

            val apicall = api.getPhotos(page, state.config.pageSize)

            if (apicall.isNotEmpty()) {

                database.withTransaction {
                    database.galleryDao().insert(apicall)
                    database.galleryDao().insertLastPage(GalleryPageData(0, page.plus(1), 0))
                }

            }

            MediatorResult.Success(endOfPaginationReached = false)

        }catch (e:Exception)
        {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getKey(): GalleryPageData?
    {
        return database.galleryDao().getPage()
    }

}

