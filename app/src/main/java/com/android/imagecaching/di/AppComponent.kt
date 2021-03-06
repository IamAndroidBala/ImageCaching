package com.android.imagecaching.di

import com.android.imagecaching.ui.userlistscreen.UserListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * register the component modules
 */
@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class, ApiModule::class])
interface AppComponent {

    fun inject(target : UserListActivity)

}