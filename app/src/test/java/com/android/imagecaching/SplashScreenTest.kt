package com.android.imagecaching

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.android.imagecaching.ui.splashscreen.SplashScreenActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SplashScreenTest {

    @Test
    fun delayTwoSecondTest() {

        val splashScreenActivity = SplashScreenActivity()

        Assert.assertFalse(splashScreenActivity.isFinishing)
        splashScreenActivity.goToHomeScreenActivity()

        Assert.assertTrue(true)

    }

    @get:Rule
    var activityRule : ActivityTestRule <SplashScreenActivity> = ActivityTestRule(SplashScreenActivity::class.java, true, false)


//
//    @get:Rule
//    val intentsTestRule = IntentsTestRule(SplashScreenActivity::class.java)
//

    @Test
    fun intent() {
        val intent = Intent()
        intent.putExtra("your_key", "your_value")
        activityRule.launchActivity(intent)
    }

}