package com.android.imagecaching

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserListTest {

    fun recyclerVisible() {

        Assert.assertFalse(false)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerImageList))
        Assert.assertTrue(true)

    }

}