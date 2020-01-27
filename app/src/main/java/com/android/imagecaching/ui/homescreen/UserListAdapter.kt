package com.android.imagecaching.ui.homescreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.imagecaching.R
import com.android.myimagecacher.imageloader.ImageLoader
import com.android.imagecaching.model.UserListModel
import com.android.imagecaching.ui.profilescreen.UserProfileActivity
import kotlinx.android.synthetic.main.item_inage_holder.view.*

class UserListAdapter (private val mContext : Context, private var mList : ArrayList<UserListModel>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var imgLoader: ImageLoader? = null

    init {
        imgLoader =  ImageLoader(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inage_holder,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(mList.get(position))
    }

    override fun getItemCount() = mList.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun onBindItem( data : UserListModel) {

            data.user?.profile_image?.large?.let { imgLoader!!.displayImage(data.user?.profile_image?.large!!, itemView.imgCaching) }

            itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext, UserProfileActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    putExtra("UserData",data )
                })
            }

        }

    }

    fun setData(data : List<UserListModel>) {
        mList.addAll(data)
        notifyDataSetChanged()
    }

}