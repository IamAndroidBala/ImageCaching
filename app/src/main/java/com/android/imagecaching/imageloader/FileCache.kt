package com.android.imagecaching.imageloader

import android.content.Context
import android.os.Environment
import java.io.File

class FileCache(context: Context) {
    private var cacheDir: File? = null
    fun getFile(url: String): File { //I identify images by hashcode. Not a perfect solution, good for the demo.
        val filename = url.hashCode().toString()
        //Another possible solution (thanks to grantland)
//String filename = URLEncoder.encode(url);
        return File(cacheDir, filename)
    }

    fun clear() {
        val files = cacheDir!!.listFiles() ?: return
        for (f in files) f.delete()
    }

    init { //Find the dir to save cached images

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(path, "DemoPicture.jpg")

        cacheDir =
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) File(Environment.getExternalStorageDirectory(), "TTImages_cache")
        else
            context.cacheDir

        if (!cacheDir!!.exists()) cacheDir!!.mkdirs()
    }
}