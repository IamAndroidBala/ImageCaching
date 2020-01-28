package com.android.imagecaching

import android.os.Build
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.android.imagecaching.model.ProfileImageModel
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.model.UserModel
import com.android.imagecaching.ui.userlistscreen.UserListActivity
import com.android.imagecaching.ui.userlistscreen.UserListAdapter
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

    private var testUrl = "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026fit=crop\u0026h=32\u0026w=32\u0026s=63f1d805cffccb834cf839c719d91702"

    private var userListModel = UserListModel("1", "01/01/2020", 1024, 1024, "",12, false,
        UserModel("1", "test", "Test", ProfileImageModel(testUrl,testUrl,testUrl), null),null, null,null,null)

    private lateinit var userListAdapter: UserListAdapter
    lateinit var recyclerView: RecyclerView

    @Before
    @Throws(java.lang.Exception::class)
    fun recyclerVisible() {

        userListActivity = Robolectric.buildActivity(UserListActivity::class.java).create().resume().get()

        recyclerView = userListActivity.findViewById(R.id.recyclerUserList)

        val arrayList = ArrayList<UserListModel>()
        arrayList.add(userListModel)

        userListAdapter            = UserListAdapter(ApplicationProvider.getApplicationContext(),arrayList)
        recyclerView.layoutManager = LinearLayoutManager(ApplicationProvider.getApplicationContext())
        recyclerView.adapter       = userListAdapter

    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(userListActivity)
    }

    @Test
    @Throws(Exception::class)
    fun performItemClick() {
        recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
    }

}