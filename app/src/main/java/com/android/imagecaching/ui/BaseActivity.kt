package com.android.imagecaching.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * This is BaseActivity for this app
 * Do the common activity stuffs here like setup toolbar and run time permission checking
 */

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(){

    var permissionsStorage   = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                showMessage(false)
            } else {
                showMessage(true)
            }
        }

    }

    /**
     * Checking either permission granted or not
     */
    fun permissionsGranted(permissions: Array<String>): Boolean {
        var granted = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                granted = false
                break
            }
        }
        return granted
    }

    /**
     * setting up the toolbar with custom title
     */
    fun getToolbar(title : String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title
    }

    /**
     * register the broadcast receiver for monitoring the network connectivity
     */
    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    /**
     * unregister the broadcast receiver
     */
    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    /**
     * this is used to show the snackbar in activity
     */
    abstract fun showMessage(isConnected: Boolean)

}