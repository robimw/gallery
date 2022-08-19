package com.appinionbd.gallery.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.appinionbd.gallery.data.network.PhotoApi
import com.appinionbd.gallery.data.network.response.GalleryResponseItem
import java.lang.Exception

/*
class GallerySource(
    private val api:PhotoApi
): PagingSource<Int, GalleryResponseItem>() {

    override fun getRefreshKey(state: PagingState<Int,GalleryResponseItem>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,GalleryResponseItem> {

        return try {

            val page:Int =params.key?:1
            val result=api.getPhotos(page)

            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if(result.isNotEmpty()) page+1 else null
            )

        }catch (e:Exception)
        {
            LoadResult.Error(e)

        }

    }
}

 */