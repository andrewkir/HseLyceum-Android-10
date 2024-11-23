package ru.andrewkir.saturday10.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andrewkir.saturday10.R
import ru.andrewkir.saturday10.data.api.ApiExample
import ru.andrewkir.saturday10.data.models.GoodsItemModel
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.AddButtonClicked
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.UpdateGoodsTextField
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.UpdateGoodsUrlField
import ru.andrewkir.saturday10.presentation.contract.GoodsState
import kotlin.random.Random


class GoodsViewModel : ViewModel() {

  val state = MutableStateFlow(GoodsState())

  init {
    val client = getClient()
    viewModelScope.launch {
      try {
        val users = client.getUsers()
        users.forEach {
          state.value = state.value.copy(
            goods = state.value.goods + listOf(
              GoodsItemModel(
                name = it.login,
                price = Random.nextInt() % 5,
                stars = Random.nextInt() % 1000,
                imageId = R.drawable.ershik,
                imageURL = state.value.goodsUrl,
              )
            ),
            goodsName = "",
            goodsUrl = "",
          )
        }
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

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
        state.value = state.value.copy(
          goods = state.value.goods + listOf(
            GoodsItemModel(
              name = state.value.goodsName,
              price = Random.nextInt() % 5,
              stars = Random.nextInt() % 1000,
              imageId = R.drawable.ershik,
              imageURL = state.value.goodsUrl,
            )
          ),
          goodsName = "",
          goodsUrl = "",
        )
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