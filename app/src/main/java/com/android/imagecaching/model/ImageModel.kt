package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ImageModel (var id  : String,
                       var created_at : String?,
                       var width : Long,
                       var height : Long,
                       var color : String,
                       var likes : Int?,
                       var liked_by_user : Boolean = false,
                       var user : UserModel?,
                       var current_user_collections : @RawValue Any?,
                       var urls : UrlsModel?,
                       var categories : List<CategoriesModel>?,
                       var links : LinksModel) : Parcelable {}