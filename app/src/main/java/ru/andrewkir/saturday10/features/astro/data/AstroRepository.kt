package ru.andrewkir.saturday10.features.astro.data

import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.andrewkir.saturday10.features.astro.data.api.AstroApi
import ru.andrewkir.saturday10.features.astro.data.model.AstroResponse

class AstroRepository {

    private val astroApi by lazy {
        getClient()
    }

    suspend fun getAstronauts(): Result<AstroResponse> {
        try {
            val astronautsResponse = astroApi.getAstronauts()
            return Result.success(astronautsResponse)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun getClient(): AstroApi {
        val httpClient = Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.open-notify.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(AstroApi::class.java)
    }
}