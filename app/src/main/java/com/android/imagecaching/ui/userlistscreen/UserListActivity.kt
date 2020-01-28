package com.android.imagecaching.ui.userlistscreen

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.imagecaching.ImageCacherApplication
import com.android.imagecaching.R
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.ui.BaseActivity
import com.android.imagecaching.utils.PaginationListener
import com.android.imagecaching.utils.errorDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * user list activity
 * call the api and display the users data in recyclerview
 */
class UserListActivity : BaseActivity(), UserLoadingViews {

    var isLoad      = false
    var isLast      = false
    var totalPage   = 1
    var currentPage = PaginationListener.PAGE_START

    var mList = ArrayList<UserListModel>()
    private var mSnackBar   : Snackbar? = null
    lateinit var userListAdapter   : UserListAdapter
    lateinit var layoutManager: LinearLayoutManager
    @Inject lateinit var imageLoaderPresenter : UserListLoadingPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ImageCacherApplication).appComponent.inject(this)

        layoutManager   = LinearLayoutManager(this)
        recyclerUserList.layoutManager = layoutManager
        userListAdapter = UserListAdapter(this@UserListActivity, mList)
        recyclerUserList.adapter = userListAdapter

        /**
         * pagination listener
         */
        recyclerUserList.addOnScrollListener(object : PaginationListener(layoutManager) {

            override fun loadMoreItems() {

                isLoad = true
                currentPage ++

                if (currentPage != PAGE_START){
                    if (currentPage < totalPage) {
                        imageLoaderPresenter.setLoading()
                    } else {
                        isLast = true
                    }
                }

            }

            override val isLastPage: Boolean
                get() = isLast

            override val isLoading: Boolean
                get() = isLoad

        })

    }

    /**
     * permission listener
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            36 -> if(permissionsGranted(permissionsStorage))  initPage() else askPermission()
        }
    }

    /**
     * setup the page
     */
    private fun initPage() {
        imageLoaderPresenter.setPage(this)
        imageLoaderPresenter.setLoading()
    }

    /**
     * request the external storage permission if not granted
     */
    private fun askPermission() {
        ActivityCompat.requestPermissions(this, permissionsStorage, 36)
    }

    /**
     * show snackbar when no internet
     */
    override fun showMessage(isConnected: Boolean) {

        if (!isConnected) {
            val messageToUser = resources.getString(R.string.offline_warning)
            mSnackBar = Snackbar.make(rlSample, messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.show()
        }

        if(permissionsGranted(permissionsStorage)) initPage() else askPermission()

    }

    /**
     * display progressbar
     */
    override fun displayLoading() {
        progressBar_wait.post {
            progressBar_wait.visibility = View.VISIBLE
            recyclerUserList.visibility = View.GONE
        }
    }

    /**
     * dismiss progressbar and show recyclerview
     */
    override fun dismissLoading() {
        progressBar_wait.post {
            progressBar_wait.visibility = View.GONE
            recyclerUserList.visibility = View.VISIBLE
        }
    }

    /**
     * get result from api and set to adapter
     */
    override fun displayResult(result : List<UserListModel>?) {

        result?.let {
            recyclerUserList.post {
                userListAdapter.setData(result)
            }
        }

    }

    /**
     * show the error msg in dialog
     */
    override fun displayError(error : String?) {
        runOnUiThread {
            R.string.error.errorDialog(this@UserListActivity)
        }
    }

}
