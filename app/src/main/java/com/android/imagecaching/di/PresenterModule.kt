package com.android.imagecaching.di

import com.android.imagecaching.network.GetImageList
import com.android.imagecaching.ui.homescreen.ImageLoadingPresenter
import com.android.imagecaching.ui.homescreen.ImageLoadingPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideImageLoadingPresenter(getImageList: GetImageList) : ImageLoadingPresenterImpl = ImageLoadingPresenter(getImageList)

}