package com.workex.userprofile.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(@SerializedName("zip")
                   val zip: String = "",
                   @SerializedName("geo")
                   val geo: Geo,
                   @SerializedName("country")
                   val country: String = "",
                   @SerializedName("city")
                   val city: String = "",
                   @SerializedName("countryCode")
                   val countryCode: String = "",
                   @SerializedName("street")
                   val street: String = "",
                   @SerializedName("state")
                   val state: String = "") : Parcelable