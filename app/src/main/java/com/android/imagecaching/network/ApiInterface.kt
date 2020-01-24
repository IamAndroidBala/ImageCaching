package com.android.imagecaching.network

import com.android.imagecaching.model.ImageModel
import com.android.imagecaching.utils.DATA_API
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(DATA_API )
    fun getPhotos()     : Call<List<ImageModel>>
}