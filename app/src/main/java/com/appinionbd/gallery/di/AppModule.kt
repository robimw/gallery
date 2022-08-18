package com.appinionbd.gallery.di

import com.appinionbd.gallery.data.network.ApiClient
import com.appinionbd.gallery.data.network.PhotoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}