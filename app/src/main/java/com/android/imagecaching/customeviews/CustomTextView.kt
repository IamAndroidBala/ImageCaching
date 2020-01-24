package com.android.imagecaching.customeviews

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.android.imagecaching.R


class CustomTextView : TextView {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?) : super(context) {
        init(null)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val customView = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
            val customFont = ResourcesCompat.getFont(context, R.font.sukhumvitset_text)
            textSize = 25f
            typeface = customFont
            customView.recycle()
        }
    }

}