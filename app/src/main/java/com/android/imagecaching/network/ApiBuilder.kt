package com.android.imagecaching.network

import com.android.imagecaching.utils.DATA_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiBuilder @Inject constructor() {

    fun getAPiBuilder() : Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DATA_API)
            .build()

    }

}