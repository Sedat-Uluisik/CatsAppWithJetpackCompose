package com.sedat.catsappwithjetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weight(
    val imperial: String ?= null,
    val metric: String ?= null
): Parcelable