package com.android.imagecaching.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.imagecaching.R
import com.android.imagecaching.ui.userlistscreen.UserListActivity

/**
 * Show the welcome message for 2 sec then go to Main activity
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToHomeScreenActivity()

    }

    fun goToHomeScreenActivity() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity,
                UserListActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
            })},2000)
    }

}