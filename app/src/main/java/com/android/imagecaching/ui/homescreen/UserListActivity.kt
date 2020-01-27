package com.android.imagecaching.ui.homescreen

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
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

        imageLoaderPresenter.setPage(this)
        imageLoaderPresenter.setLoading()

        imageListAdapter = UserListAdapter(this@UserListActivity, mList)
        recyclerImageList.adapter = imageListAdapter

        setLayoutConfig()

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
            //recyclerImageList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
