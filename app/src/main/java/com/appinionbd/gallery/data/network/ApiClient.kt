package com.appinionbd.gallery.data.network

import com.appinionbd.gallery.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiClient @Inject constructor()
{
    companion object
    {
        private const val BASE_URL="https://api.unsplash.com/"
    }

    fun <T> buildClient(api:Class<T>):T
    {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor
                {
                    chain-> chain.proceed(
                            chain.request().newBuilder().also {
                                it.header("Authorization","Client-ID Fr6Nw3QLTlgLZ5bXGoaB_vau-hUdtaFlSmGOdrIiU5o")
                                it.header("Accept-Version","v1")
                            }.build()
                        )

                }.also {
                    client->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }

                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}