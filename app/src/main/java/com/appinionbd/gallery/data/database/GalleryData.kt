package com.appinionbd.gallery.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appinionbd.gallery.data.network.response.Urls

@Entity(tableName = "photo")
data class GalleryData(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "urls")
    val urls: Urls,

)