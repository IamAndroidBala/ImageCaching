package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinksModel(var self : String?, var html : String?, var photos : String?, var likes : String?, var download : String?) : Parcelable{}