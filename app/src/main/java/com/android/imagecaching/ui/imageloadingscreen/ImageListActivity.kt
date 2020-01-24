package com.android.imagecaching.ui.imageloadingscreen

import android.content.res.Configuration
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.imagecaching.R
import com.android.imagecaching.ui.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class ImageListActivity : BaseActivity() {

   var mList = ArrayList<ImageModel>()
    private var mSnackBar: Snackbar? = null
    lateinit var imageListAdapter: ImageListAdapter
    private val url = "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=faces\\u0026fit=crop\\u0026h=32\\u0026w=32\\u0026s=63f1d805cffccb834cf839c719d91702"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (s in 1..10) {
            val imageModel =
                ImageModel(url)
            mList.add(imageModel)
        }

        imageListAdapter = ImageListAdapter(this@ImageListActivity, mList)
        recyclerImageList.adapter = imageListAdapter

        setLayoutConfig()

    }

    override fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now"
            mSnackBar = Snackbar.make(rlSample, messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }

    private fun setLayoutConfig() {

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerImageList.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerImageList.layoutManager = GridLayoutManager(this, 2)
        }

    }

}
