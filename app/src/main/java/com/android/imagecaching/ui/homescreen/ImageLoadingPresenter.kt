package com.android.imagecaching.ui.homescreen

import com.android.imagecaching.model.ImageModel
import com.android.imagecaching.network.ApiInterface
import com.android.imagecaching.network.GetImageList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ImageLoadingPresenter @Inject constructor(private val getImageList: GetImageList) : ImageLoadingPresenterImpl {

    lateinit var imageLoadingViews: ImageLoadingViews

    override fun setLoading() {

        imageLoadingViews.dismissLoading()

        getImageList.getData().create(ApiInterface::class.java).getPhotos().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                imageLoadingViews.displayResult(response.body())
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                imageLoadingViews.displayError(t.localizedMessage)
            }
        })

    }

    override fun setPage(imageLoadingViews: ImageLoadingViews) {
        this.imageLoadingViews = imageLoadingViews
    }

}