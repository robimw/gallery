package com.appinionbd.gallery.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [GalleryData::class,GalleryPageData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RoomDB:RoomDatabase()
{
    abstract fun galleryDao() : GalleryDao
}
