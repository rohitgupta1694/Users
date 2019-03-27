package com.workex.userprofile.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FriendsItem(@SerializedName("id")
                       val id: Int = 0) : Parcelable