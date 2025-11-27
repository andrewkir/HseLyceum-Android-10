package ru.andrewkir.saturday10.features.astro.data

import ru.andrewkir.saturday10.features.astro.data.api.AstroApi
import ru.andrewkir.saturday10.features.astro.data.model.AstroResponse
import javax.inject.Inject

class AstroRepository @Inject constructor(
    private val astroApi: AstroApi
) {
    suspend fun getAstronauts(): Result<AstroResponse> {
        try {
            val astronautsResponse = astroApi.getAstronauts()
            return Result.success(astronautsResponse)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}