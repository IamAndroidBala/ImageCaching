package com.android.imagecaching

import android.app.Application
import com.android.imagecaching.di.AppComponent
import com.android.imagecaching.di.AppModule
import com.android.imagecaching.di.DaggerAppComponent

class ImageCacherApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)

    }

    private fun initDagger(app: ImageCacherApplication): AppComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()

}