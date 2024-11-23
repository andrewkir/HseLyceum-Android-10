package ru.andrewkir.saturday10.data.api

import retrofit2.http.GET
import ru.andrewkir.saturday10.data.models.UserModel

interface ApiExample {

  @GET("/users?since=<string>&per_page=30")
  suspend fun getUsers(): List<UserModel>
}