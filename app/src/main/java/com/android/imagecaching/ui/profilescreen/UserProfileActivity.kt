package com.android.imagecaching.ui.profilescreen

import android.os.Bundle
import com.android.imagecaching.R
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.ui.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class UserProfileActivity : BaseActivity() {

    lateinit var userListModel: UserListModel
    private var mSnackBar   : Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        if(intent?.extras?.getParcelable<UserListModel>("UserData") != null) {
            userListModel = intent?.extras?.getParcelable("UserData")!!
        }

        userListModel.user?.name?.let { getToolbar(userListModel.user?.name!!) } ?: getToolbar(resources.getString(R.string.profile))

    }

    override fun showMessage(isConnected: Boolean) {

        if (!isConnected) {
            val messageToUser = resources.getString(R.string.offline_warning)
            mSnackBar = Snackbar.make(rlSample, messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }

    }

}