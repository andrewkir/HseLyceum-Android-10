package ru.andrewkir.saturday10.features.github.data.model

import com.google.gson.annotations.SerializedName

data class GithubUserModel(
  @SerializedName("id")
  val id: Int,
  @SerializedName("login")
  val login: String,
  @SerializedName("avatar_url")
  val avatarUrl: String,
  @SerializedName("html_url")
  val url: String
)