package com.appinionbd.gallery.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface GalleryDao
{

    @Query("SELECT * FROM photo")
    fun getData(): PagingSource<Int,GalleryData>

    @Insert(onConflict = REPLACE)
    suspend fun insert(image:ArrayList<GalleryData>)

    @Insert(onConflict = REPLACE)
    suspend fun insertLastPage(data:GalleryPageData)

    @Query("SELECT * FROM page")
    suspend fun getPage(): GalleryPageData

}