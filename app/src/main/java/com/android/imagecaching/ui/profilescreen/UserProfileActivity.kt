package com.android.imagecaching.ui.profilescreen

import android.os.Bundle
import android.view.MenuItem
import com.android.imagecaching.R
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.ui.BaseActivity
import com.android.imagecaching.utils.AppLog
import com.android.myimagecacher.imageloader.ImageLoader
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_profile.*

/**
 * profile screen
 * receive data from UserListAdapter
 */
class UserProfileActivity : BaseActivity() {

    private lateinit var userListModel: UserListModel
    private var mSnackBar   : Snackbar? = null
    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        if(intent?.extras?.getParcelable<UserListModel>("UserData") != null) {
            userListModel = intent?.extras?.getParcelable("UserData")!!
        }

        imageLoader = ImageLoader(this)

        userListModel.user?.name?.let { getToolbar(userListModel.user?.name!!) } ?: getToolbar(resources.getString(R.string.profile))

        userListModel.user?.profile_image?.large?.let { imageLoader.displayImage(userListModel.user?.profile_image?.large!!,imageUserProfile,progressProfileImageLoading) }

        userListModel.user?.name?.let {  tvProfileName.text = userListModel.user?.name }

        userListModel.likes?.let { tvProfileLikes.text = "${userListModel.likes}" }

    }

    /**
     * showing network connection status
     */
    override fun showMessage(isConnected: Boolean) {

        if (!isConnected) {
            val messageToUser = resources.getString(R.string.offline_warning)
            mSnackBar = Snackbar.make(rlProfilePage, messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.show()
        }

    }


    /**
     * back button operation
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}