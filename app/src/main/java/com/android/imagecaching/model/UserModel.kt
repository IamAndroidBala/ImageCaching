package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (var id : String,
                      var username : String,
                      var name : String,
                      var profile_image: ProfileImageModel?,
                      var links : LinksModel?
                      ): Parcelable {}