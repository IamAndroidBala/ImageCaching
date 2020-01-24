package com.android.imagecaching.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesModel (var id : Int,
                            var title : String,
                            var photo_count : Int,
                            var links : LinksModel?
                            ) : Parcelable {}