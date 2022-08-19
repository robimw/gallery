package com.appinionbd.gallery.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
data class GalleryPageData(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:Int,

    @ColumnInfo(name = "next")
    var next:Int?,

    @ColumnInfo(name = "prev")
    var prev:Int?

)