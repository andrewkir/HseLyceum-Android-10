package ru.andrewkir.saturday10.features.astro.data.model

import com.google.gson.annotations.SerializedName

data class AstroResponse(
    @SerializedName("people") val astronauts: List<Astronaut>,
) {

    data class Astronaut(
        @SerializedName("craft") val craft: String,
        @SerializedName("name") val name: String,
    )
}
