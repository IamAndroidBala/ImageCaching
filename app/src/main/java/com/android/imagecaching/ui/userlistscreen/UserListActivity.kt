package com.android.imagecaching.ui.userlistscreen

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.android.imagecaching.ImageCacherApplication
import com.android.imagecaching.R
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.ui.BaseActivity
import com.android.imagecaching.utils.errorDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class UserListActivity : BaseActivity(), UserLoadingViews {

    var mList = ArrayList<UserListModel>()
    private var mSnackBar   : Snackbar? = null
    lateinit var imageListAdapter   : UserListAdapter
    @Inject lateinit var imageLoaderPresenter : UserListLoadingPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ImageCacherApplication).appComponent.inject(this)

        if(permissionsGranted(permissionsStorage)) {
           initPage()
        } else {
           askPermission()
        }

        imageListAdapter = UserListAdapter(this@UserListActivity, mList)
        recyclerImageList.adapter = imageListAdapter

        setLayoutConfig()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            36 -> if(permissionsGranted(permissionsStorage))  initPage() else askPermission()
        }
    }

    private fun initPage() {
        imageLoaderPresenter.setPage(this)
        imageLoaderPresenter.setLoading()
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(this, permissionsStorage, 36)
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

    private fun setLayoutConfig() {

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //recyclerImageList.layoutManager = LinearLayoutManager(this)
            recyclerImageList.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerImageList.layoutManager = GridLayoutManager(this, 3)
        }

    }

    override fun displayLoading() {
        progressBar_wait.post {
            progressBar_wait.visibility = View.VISIBLE
            recyclerImageList.visibility = View.GONE
        }
    }

    override fun dismissLoading() {
        progressBar_wait.post {
            progressBar_wait.visibility = View.GONE
            recyclerImageList.visibility = View.VISIBLE
        }
    }

    override fun displayResult(result : List<UserListModel>?) {
        result?.let {
            recyclerImageList.post {
                imageListAdapter.setData(result)
            }
        }
    }

    override fun displayError(error : String?) {
        runOnUiThread {
            R.string.error.errorDialog(this@UserListActivity)
        }
    }

}
