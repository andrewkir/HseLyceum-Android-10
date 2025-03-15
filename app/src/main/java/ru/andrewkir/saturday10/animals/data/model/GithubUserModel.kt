package ru.andrewkir.saturday10.animals.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserModel(
  @SerializedName("id")
  val id: Int,
  @SerializedName("login")
  val login: String,
  @SerializedName("avatar_url")
  val avatarUrl: String,
)