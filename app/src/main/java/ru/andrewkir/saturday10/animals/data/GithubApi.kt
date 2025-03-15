package ru.andrewkir.saturday10.animals.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.andrewkir.saturday10.animals.data.model.GithubUserModel


interface GithubApi {

  @GET("/users")
  suspend fun getUsers(
    @Query("since") since: Int,
    @Query("per_page") count: Int = 30
  ): List<GithubUserModel>
}