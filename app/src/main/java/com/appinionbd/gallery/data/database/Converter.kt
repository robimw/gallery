package com.appinionbd.gallery.data.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.appinionbd.gallery.data.network.response.Urls
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class Converter {

    @TypeConverter
    fun fromBitmap(bmp: Bitmap): ByteArray{

        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


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