package com.android.imagecaching.utils

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.imagecaching.R

class CommonMethods {

    companion object {

        fun showToastWithTitleAndContext(mContext: Context, title: String?) {

            val toast = Toast.makeText(mContext, title, Toast.LENGTH_SHORT)
            val view = toast.view
            view.background.setColorFilter(
                ContextCompat.getColor(mContext, R.color.colorPrimary),
                PorterDuff.Mode.SRC_IN
            )
            val text = view.findViewById<TextView>(R.id.message)
            text.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite))
            toast.show()

        }
    }

}