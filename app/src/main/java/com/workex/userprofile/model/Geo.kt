package com.workex.userprofile.model

import com.google.gson.annotations.SerializedName

data class Geo(@SerializedName("latitude")
               val latitude: Double = 0.0,
               @SerializedName("longitude")
               val longitude: Double = 0.0)