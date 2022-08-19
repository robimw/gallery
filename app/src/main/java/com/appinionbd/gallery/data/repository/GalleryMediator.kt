package com.appinionbd.gallery.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.appinionbd.gallery.data.database.GalleryDao
import com.appinionbd.gallery.data.database.GalleryData
import com.appinionbd.gallery.data.database.GalleryPageData
import com.appinionbd.gallery.data.database.RoomDB
import com.appinionbd.gallery.data.network.PhotoApi
import com.appinionbd.gallery.data.network.response.GalleryResponseItem
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


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
                LoadType.REFRESH -> null
                LoadType.PREPEND -> null
                LoadType.APPEND ->
                {
                    state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                      val loadpage=  getKey()

                    val page = loadpage.next?:1

                    val apicall = api.getPhotos(page)


                    if(apicall.isNotEmpty()) {

                        Log.e("page number",page.toString())

                        withContext(Dispatchers.IO)
                        {

                            try {
                                galleryDao.insert(apicall)
                                galleryDao.insertLastPage(GalleryPageData(0,page.plus(1),0))
                                Log.e("database","entry okay")

                            }
                            catch (e:Exception)
                            {
                                Log.e("database",e.toString())

                            }

                        }
                    }
                }
            }


            MediatorResult.Success(endOfPaginationReached = true)

        }catch (e:Exception)
        {
            Log.e("apierror",e.toString())
            MediatorResult.Error(e)
        }
    }

    private suspend fun getKey(): GalleryPageData
    {
        return galleryDao.getPage()


    }

     private  fun getImage(imgurl: String): Bitmap? {
        val url: URL = mStringToURL(imgurl)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

}

