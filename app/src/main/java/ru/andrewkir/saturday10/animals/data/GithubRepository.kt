package ru.andrewkir.saturday10.animals.data

import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andrewkir.saturday10.animals.data.model.GithubUserModel
import kotlin.random.Random

class GithubRepository {
  val api: GithubApi by lazy {
    getClient()
  }

  suspend fun getUsers(): Result<List<GithubUserModel>> {
    try {
      val users = api.getUsers(Random.nextInt(1000))
      return Result.success(users)
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }

  private fun getClient(): GithubApi {
    val httpClient = Builder()
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient.build())
      .build()

    return retrofit.create(GithubApi::class.java)
  }
}