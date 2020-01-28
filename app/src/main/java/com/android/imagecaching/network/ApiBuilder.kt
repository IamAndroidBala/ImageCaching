package com.android.imagecaching.network

import com.android.imagecaching.ImageCacherApplication
import com.android.imagecaching.utils.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiBuilder @Inject constructor() {

    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache   = Cache(ImageCacherApplication.instance.cacheDir, cacheSize)

    /**
     * Create okHttpClient for cache the response for offline usage
     */
    private val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (ImageCacherApplication().hasNetwork(ImageCacherApplication.instance)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            chain.proceed(request)
        }
        .build()

    fun getApiBuilder(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

}