package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileImageModel(var small : String?, var medium : String?, var large : String?) : Parcelable{}