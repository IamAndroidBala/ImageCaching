package com.android.imagecaching

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.android.imagecaching.di.AppComponent
import com.android.imagecaching.di.AppModule
import com.android.imagecaching.di.DaggerAppComponent

class ImageCacherApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var instance: ImageCacherApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance     = this
        appComponent = initDagger(this)

    }

    /**
     * init dagger 2
     */
    private fun initDagger(app: ImageCacherApplication): AppComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()

    /**
     * used to check the internet available or not
     */
    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

}