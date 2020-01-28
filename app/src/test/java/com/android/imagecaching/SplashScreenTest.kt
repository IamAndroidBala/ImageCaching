package com.android.imagecaching

import android.content.Intent
import com.android.imagecaching.ui.splashscreen.SplashScreenActivity
import com.android.imagecaching.ui.userlistscreen.UserListActivity
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows


@RunWith(RobolectricTestRunner::class)
class SplashScreenTest {

    private lateinit var splashScreenActivity: SplashScreenActivity

    @Before
    @Throws(java.lang.Exception::class)
    fun recyclerVisible() {

        splashScreenActivity = Robolectric.buildActivity(SplashScreenActivity::class.java).create().resume().get()


    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveCorrectWelcomeMsg() {
        val text : String = splashScreenActivity.resources.getString(R.string.welcome)
        Assert.assertThat(text, CoreMatchers.equalTo("Welcome to ImageCaching"))
    }

    @Test
    @Throws(Exception::class)
    fun goToMainScreen() {

        val intent: Intent = Shadows.shadowOf(splashScreenActivity).nextStartedActivity
        Assert.assertEquals(UserListActivity::class.java.canonicalName, intent.component?.className)

    }

}