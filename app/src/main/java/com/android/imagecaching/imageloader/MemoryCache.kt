package com.android.imagecaching.imageloader

import android.graphics.Bitmap
import android.util.Log
import com.android.imagecaching.utils.AppLog
import java.util.*

class MemoryCache {
    private val cache = Collections.synchronizedMap(LinkedHashMap<String, Bitmap?>(10, 1.5f, true)) //Last argument true for LRU ordering
    private var size: Long = 0 //current allocated size
    private var limit: Long = 1000000 //max memory in bytes

    private fun setLimit(new_limit: Long) {
        limit = new_limit
        AppLog.d("MemoryCache will use up to " + limit / 1024.0 / 1024.0 + "MB")
    }

    operator fun get(id: String?): Bitmap? {
        return try {
            if (!cache.containsKey(id)) null else cache[id]
            //NullPointerException sometimes happen here http://code.google.com/p/osmdroid/issues/detail?id=78
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
            null
        }
    }

    fun put(id: String, bitmap: Bitmap?) {
        try {
            if (cache.containsKey(id)) size -= getSizeInBytes(cache[id])
            cache[id] = bitmap
            size += getSizeInBytes(bitmap)
            checkSize()
        } catch (th: Throwable) {
            th.printStackTrace()
        }
    }

    private fun checkSize() {

        AppLog.d("cache size=" + size + " length=" + cache.size)

        if (size > limit) {
            val iter: MutableIterator<Map.Entry<String, Bitmap?>> =
                cache.entries.iterator() //least recently accessed item will be the first one iterated
            while (iter.hasNext()) {
                val entry = iter.next()
                size -= getSizeInBytes(entry.value)
                iter.remove()
                if (size <= limit) break
            }
            AppLog.d("Clean cache. New size " + cache.size)
        }
    }

    fun clear() {
        try { //NullPointerException sometimes happen here http://code.google.com/p/osmdroid/issues/detail?id=78
            cache.clear()
            size = 0
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }

    private fun getSizeInBytes(bitmap: Bitmap?): Long {
        return if (bitmap == null) 0 else (bitmap.rowBytes * bitmap.height).toLong()
    }

    init { //use 25% of available heap size
        setLimit(Runtime.getRuntime().maxMemory() / 4)
    }
}