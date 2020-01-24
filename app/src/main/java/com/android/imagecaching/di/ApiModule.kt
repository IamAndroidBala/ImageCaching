package com.android.imagecaching.di

import com.android.imagecaching.network.ApiBuilder
import com.android.imagecaching.network.GetImageList
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideImageLoadingApi( api : ApiBuilder) = GetImageList(api)

}