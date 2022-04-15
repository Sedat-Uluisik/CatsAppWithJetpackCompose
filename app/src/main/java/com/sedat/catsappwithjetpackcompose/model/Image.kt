package com.sedat.catsappwithjetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    var height: Int ?= null,
    var id: String ?= null,
    var url: String ?= null,
    var width: Int ?= null
): Parcelable