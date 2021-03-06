package com.android.imagecaching.ui.userlistscreen

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
import kotlinx.android.synthetic.main.item_image_holder.view.*

/**
 * populating the user details
 */
class UserListAdapter (private val mContext : Context, private var mList : ArrayList<UserListModel>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var imgLoader: ImageLoader? = null

    init {
        imgLoader =  ImageLoader(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_holder,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(mList.get(position))
    }

    override fun getItemCount() = mList.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun onBindItem( data : UserListModel) {

            data.user?.name?.let { itemView.tvUserName.text = data.user?.name }
            data.likes?.let { itemView.tvUserLikes.text = "${data.likes}" }?: kotlin.run { itemView.tvUserLikes.text = "0" }
            data.user?.profile_image?.large?.let { imgLoader!!.displayImage(data.user?.profile_image?.large!!, itemView.imgCaching, itemView.progressImageLoading) }

            itemView.rlUserItem.setOnClickListener {
                data.user?.id?.let {
                    mContext.startActivity(Intent(mContext, UserProfileActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        putExtra("UserData",data )
                    })
                }
            }

        }

    }

    fun setData(data : List<UserListModel>) {
        mList.addAll(data)
        notifyDataSetChanged()
    }

}