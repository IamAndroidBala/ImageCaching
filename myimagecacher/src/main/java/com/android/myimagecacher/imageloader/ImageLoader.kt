package com.android.myimagecacher.imageloader

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.myimagecacher.R
import com.android.myimagecacher.imageloader.Utils.copyStream
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageLoader(context: Context?) {

    var fileCache: FileCache
    var memoryCache = MemoryCache()
    var executorService: ExecutorService
    val stubId = R.mipmap.ic_launcher
    private val imageViews = Collections.synchronizedMap(WeakHashMap<ImageView, String>())

    fun displayImage(url: String, imageView: ImageView, progressBar: ProgressBar) {
        imageViews[imageView] = url
        val bitmap = memoryCache[url]
        if (bitmap != null) imageView.setImageBitmap(bitmap) else {
            queuePhoto(url, imageView, progressBar)
            imageView.setImageResource(stubId)
        }
    }

    private fun queuePhoto(url: String, imageView: ImageView, progressBar: ProgressBar) {
        val p = PhotoToLoad(url, imageView,progressBar)
        executorService.submit(PhotosLoader(p))
    }

    private fun getBitmap(url: String): Bitmap? {
        val f = fileCache.getFile(url)
        //from SD cache
        val b = decodeFile(f)
        return b
            ?: try {
                var bitmap: Bitmap? = null
                val imageUrl = URL(url)
                val conn = imageUrl.openConnection() as HttpURLConnection
                conn.connectTimeout = 30000
                conn.readTimeout = 30000
                conn.instanceFollowRedirects = true
                val `is` = conn.inputStream
                val os: OutputStream = FileOutputStream(f)
                copyStream(`is`, os)
                os.close()
                bitmap = decodeFile(f)
                bitmap
            } catch (ex: Throwable) {
                ex.printStackTrace()
                if (ex is OutOfMemoryError) memoryCache.clear()
                null
            }
        //from web
    }

    //decodes image and scales it to reduce memory consumption
    private fun decodeFile(f: File): Bitmap? {
        try {
            //decode image size
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(FileInputStream(f), null, o)

            //Find the correct scale value. It should be the power of 2.
            val REQUIRED_SIZE = 70
            var widthTmp  = o.outWidth
            var heightTmp = o.outHeight
            var scale = 1

            while (true) {
                if (widthTmp / 2 < REQUIRED_SIZE || heightTmp / 2 < REQUIRED_SIZE) break
                widthTmp /= 2
                heightTmp /= 2
                scale *= 2
            }
            //decode with inSampleSize
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
        } catch (e: FileNotFoundException) { }
        return null
    }

    //Task for the queue
    inner class PhotoToLoad(var url: String, var imageView: ImageView, var progressBar: ProgressBar)

    internal inner class PhotosLoader(var photoToLoad: PhotoToLoad) : Runnable {
        override fun run() {
            if (imageViewReused(photoToLoad))
                return

            val bmp = getBitmap(photoToLoad.url)
            memoryCache.put(photoToLoad.url, bmp)

            if (imageViewReused(photoToLoad))
                return

            val bd = BitmapDisplay(bmp, photoToLoad)
            val a  = photoToLoad.imageView.context as Activity
            a.runOnUiThread(bd)
        }
    }

    fun imageViewReused(photoToLoad: PhotoToLoad): Boolean {
        val tag = imageViews[photoToLoad.imageView]
        photoToLoad.progressBar.visibility = View.GONE
        return if (tag == null || tag != photoToLoad.url) true else false
    }

    //Used to display bitmap in the UI thread
    internal inner class BitmapDisplay(var bitmap: Bitmap?, var photoToLoad: PhotoToLoad) : Runnable {

        override fun run() {

            photoToLoad.progressBar.visibility = View.GONE

            if (imageViewReused(photoToLoad)) return

            if (bitmap != null)
                photoToLoad.imageView.setImageBitmap(bitmap)
            else
                photoToLoad.imageView.setImageResource(stubId)

        }

    }

    fun clearCache() {
        memoryCache.clear()
        fileCache.clear()
    }

    init {
        fileCache = FileCache(context!!)
        executorService = Executors.newFixedThreadPool(5)
    }

}