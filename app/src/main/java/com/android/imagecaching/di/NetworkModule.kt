package com.android.imagecaching.di

import com.android.imagecaching.network.ApiBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi() = ApiBuilder()

}