package com.example.weatherapp.data.remote.accuWeather.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class NameDto (
    @SerializedName("LocalizedName")
    val name: String
    )