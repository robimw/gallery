package com.appinionbd.gallery.di

import android.content.Context
import androidx.room.Room
import com.appinionbd.gallery.data.database.RoomDB
import com.appinionbd.gallery.data.network.ApiClient
import com.appinionbd.gallery.data.network.PhotoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{

    @Singleton
    @Provides
    fun provideMainApi(apiclient:ApiClient):PhotoApi
    {
        return apiclient.buildClient(PhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context.applicationContext, RoomDB::class.java,"gallery.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideGalleryDao(database:RoomDB) = database.galleryDao()


}