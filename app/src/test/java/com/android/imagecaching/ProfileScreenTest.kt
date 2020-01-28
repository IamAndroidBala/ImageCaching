package com.android.imagecaching

import android.widget.ProgressBar
import android.widget.TextView
import com.android.imagecaching.ui.profilescreen.UserProfileActivity
import com.android.imagecaching.ui.splashscreen.SplashScreenActivity
import com.android.myimagecacher.imageloader.ImageLoader
import de.hdodenhof.circleimageview.CircleImageView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.lang.Exception

@RunWith(RobolectricTestRunner::class)
class ProfileScreenTest {

    private lateinit var tvName      : TextView
    private lateinit var imageLoader : ImageLoader
    private lateinit var progressBar : ProgressBar
    private lateinit var imgProfile  : CircleImageView
    private lateinit var userProfileActivity: UserProfileActivity
    private var testUrl = "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026fit=crop\u0026h=32\u0026w=32\u0026s=63f1d805cffccb834cf839c719d91702"

    @Before
    @Throws(java.lang.Exception::class)
    fun recyclerVisible() {

        userProfileActivity = Robolectric.buildActivity(UserProfileActivity::class.java).create().resume().get()

        imageLoader = ImageLoader(userProfileActivity)

        tvName      = userProfileActivity.findViewById(R.id.tvProfileName)
        imgProfile  = userProfileActivity.findViewById(R.id.progressProfileImageLoading)
        progressBar = userProfileActivity.findViewById(R.id.progressProfileImageLoading)

    }

    @Test
    @Throws(Exception::class)
    fun loadImageTest() {
        imageLoader.displayImage(testUrl,imgProfile, progressBar)
    }

    @Test
    @Throws(Exception::class)
    fun setName() {
        Assert.assertNotNull(tvName)
        tvName.text = userProfileActivity.getString(R.string.user_name)

    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(userProfileActivity)
    }

}