package ru.andrewkir.saturday10.features.astro.data.api

import retrofit2.http.GET
import ru.andrewkir.saturday10.features.astro.data.model.AstroResponse

interface AstroApi {

    @GET("/astros.json")
    suspend fun getAstronauts(): AstroResponse
}