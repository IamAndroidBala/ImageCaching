package com.android.imagecaching.ui.homescreen

import com.android.imagecaching.model.ImageModel

interface ImageLoadingViews {

    fun displayLoading()

    fun dismissLoading()

    fun displayResult(result : List<ImageModel>?)

    fun displayError(error : String?)

}