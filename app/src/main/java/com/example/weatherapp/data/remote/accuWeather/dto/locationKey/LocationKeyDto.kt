package com.example.weatherapp.data.remote.accuWeather.dto.locationKey

import com.example.weatherapp.data.remote.accuWeather.dto.NameDto
import com.google.gson.annotations.SerializedName

// Gson inloc de Moshi
data class LocationKeyDto (

    @SerializedName("Key")
    val locationKey: String,

    @SerializedName("LocalizedName")
    val city: String,

    @SerializedName("Country")
    val cuntry: NameDto? = NameDto(""),

    @SerializedName("AdministrativeArea")
    val administrative: NameDto? = NameDto("")


)