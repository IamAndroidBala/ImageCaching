package com.android.imagecaching.ui.homescreen

import com.android.imagecaching.model.UserListModel

interface UserLoadingViews {

    fun displayLoading()

    fun dismissLoading()

    fun displayResult(result : List<UserListModel>?)

    fun displayError(error : String?)

}