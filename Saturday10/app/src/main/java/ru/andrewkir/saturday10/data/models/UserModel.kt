package ru.andrewkir.saturday10.data.models

import com.google.gson.annotations.SerializedName

data class UserModel(

  @SerializedName("login")
  val login: String,

  @SerializedName("id")
  val id: String,

  @SerializedName("avatar_url")
  val imageUrl: String,
)
