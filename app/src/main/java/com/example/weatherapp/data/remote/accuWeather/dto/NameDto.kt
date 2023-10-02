package com.example.weatherapp.data.remote.accuWeather.dto

import com.google.gson.annotations.SerializedName

data class NameDto (
    @SerializedName("LocalizedName")
    val name: String
    )