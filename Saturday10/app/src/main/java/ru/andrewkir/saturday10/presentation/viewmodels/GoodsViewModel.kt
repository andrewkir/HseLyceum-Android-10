package ru.andrewkir.saturday10.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andrewkir.saturday10.data.api.ApiExample
import ru.andrewkir.saturday10.data.models.UserModel
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.AddButtonClicked
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.UpdateGoodsTextField
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.UpdateGoodsUrlField
import ru.andrewkir.saturday10.presentation.contract.GoodsState


class GoodsViewModel : ViewModel() {

  val state = MutableStateFlow(GoodsState())


  private fun getClient(): ApiExample {
    val httpClient = Builder()
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient.build())
      .build()

    return retrofit.create(ApiExample::class.java)
  }

  fun handleEvent(event: GoodsEvent) {
    when (event) {
      is UpdateGoodsTextField -> {
        state.value = state.value.copy(goodsName = event.text)
      }

      AddButtonClicked -> {
        val client = getClient()
        viewModelScope.launch {
          try {
            val users = client.getUsers()
            users.forEach {
              state.value = state.value.copy(
                users = state.value.users + listOf(
                  UserModel(
                    login = it.login,
                    id = it.id,
                    imageUrl = it.imageUrl
                  )
                )
              )
            }
          } catch (e: Exception) {
            e.printStackTrace()
          }
        }
      }

      is UpdateGoodsUrlField -> {
        state.value = state.value.copy(goodsUrl = event.url)
      }
    }
  }

  // if(isNumeric(event.text)) {
  //  state.value = ...
  // }

  fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
  }
}