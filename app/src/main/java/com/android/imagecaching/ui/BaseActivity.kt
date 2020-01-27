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

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(){

    private var isNetworkAvailable = true
    var permissionsStorage   = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                isNetworkAvailable = false
                showMessage(false)
            } else {
                isNetworkAvailable = true
                showMessage(true)
            }
        }

    }

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

    fun getToolbar(title : String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title
    }

    fun getNetworkStatus() = isNetworkAvailable

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    abstract fun showMessage(isConnected: Boolean)


}