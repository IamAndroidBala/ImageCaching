package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UrlsModel(var raw : String? , var full : String?, var regular : String?, var small : String?, var thumb : String?) : Parcelable{}