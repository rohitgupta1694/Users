package com.workex.userprofile.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("lastName")
                val lastName: String = "",
                @SerializedName("website")
                val website: String = "",
                @SerializedName("address")
                val address: Address,
                @SerializedName("ip")
                val ip: String = "",
                @SerializedName("avatar")
                val avatar: String = "",
                @SerializedName("userName")
                val userName: String = "",
                @SerializedName("friends")
                val friends: List<FriendsItem>?,
                @SerializedName("firstName")
                val firstName: String = "",
                @SerializedName("password")
                val password: String = "",
                @SerializedName("phone")
                val phone: String = "",
                @SerializedName("dob")
                val dob: String = "",
                @SerializedName("gravatar")
                val gravatar: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("email")
                val email: String = "",
                @SerializedName("status")
                val status: Boolean = false)