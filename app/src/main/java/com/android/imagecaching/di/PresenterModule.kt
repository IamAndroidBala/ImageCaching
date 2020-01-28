package com.android.imagecaching.di

import com.android.imagecaching.network.GetUserList
import com.android.imagecaching.ui.userlistscreen.UserListLoadingPresenter
import com.android.imagecaching.ui.userlistscreen.UserListLoadingPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * presenter module
 */
@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideImageLoadingPresenter(getImageList: GetUserList) : UserListLoadingPresenterImpl = UserListLoadingPresenter(getImageList)

}