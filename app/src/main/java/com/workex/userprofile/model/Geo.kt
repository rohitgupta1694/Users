package com.workex.userprofile.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(@SerializedName("latitude")
               val latitude: Double = 0.0,
               @SerializedName("longitude")
               val longitude: Double = 0.0) : Parcelable