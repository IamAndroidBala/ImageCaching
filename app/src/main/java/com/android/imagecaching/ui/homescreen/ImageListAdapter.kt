package com.android.imagecaching.ui.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.imagecaching.R
import com.android.imagecaching.imageloader.ImageLoader
import com.android.imagecaching.model.ImageModel
import kotlinx.android.synthetic.main.item_inage_holder.view.*

class ImageListAdapter (private val mContext : Context, private var mList : ArrayList<ImageModel>) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

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

        fun onBindItem(imageData : ImageModel) {

            imageData.user?.profile_image?.large?.let { imgLoader!!.displayImage(imageData.user?.profile_image?.large!!, itemView.imgCaching) }

        }

    }

    fun setData(data : List<ImageModel>) {
        mList.addAll(data)
        notifyDataSetChanged()
    }

}