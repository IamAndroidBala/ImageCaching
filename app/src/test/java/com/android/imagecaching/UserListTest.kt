package com.android.imagecaching

import android.os.Build
import com.android.imagecaching.ui.userlistscreen.UserListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class UserListTest {

    private lateinit var userListActivity: UserListActivity

    @Before
    @Throws(java.lang.Exception::class)
    fun recyclerVisible() {

        userListActivity = Robolectric.buildActivity(UserListActivity::class.java).create().resume().get()

    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(userListActivity)
    }

    @Test
    @Throws(Exception::class)
    fun performItemClick() {
        userListActivity.recyclerUserList.getChildAt(0).performClick()
    }

}