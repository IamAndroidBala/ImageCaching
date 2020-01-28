package com.android.imagecaching.ui.userlistscreen

import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.network.ApiInterface
import com.android.imagecaching.network.GetUserList
import com.android.imagecaching.utils.AppLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * User list presenter
 */
class UserListLoadingPresenter @Inject constructor(private val getImageList: GetUserList) : UserListLoadingPresenterImpl {

    lateinit var imageLoadingViews: UserLoadingViews

    override fun setLoading() {

        imageLoadingViews.dismissLoading()

        getImageList.getData().create(ApiInterface::class.java).getPhotos().enqueue(object : Callback<List<UserListModel>> {
            override fun onResponse(call: Call<List<UserListModel>>, response: Response<List<UserListModel>>) {
                AppLog.d("okaidiidididid ")
                imageLoadingViews.displayResult(response.body())
            }

            override fun onFailure(call: Call<List<UserListModel>>, t: Throwable) {
                imageLoadingViews.displayError(t.localizedMessage)
            }
        })

    }

    override fun setPage(imageLoadingViews: UserLoadingViews) {
        this.imageLoadingViews = imageLoadingViews
    }

}