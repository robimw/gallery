package com.appinionbd.gallery.data.database

import androidx.room.TypeConverter
import com.appinionbd.gallery.data.network.response.Urls
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromString(value: String): Urls {
        val listType = object : TypeToken<Urls>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: Urls): String {
        val gson = Gson()
        return gson.toJson(list)
    }


}