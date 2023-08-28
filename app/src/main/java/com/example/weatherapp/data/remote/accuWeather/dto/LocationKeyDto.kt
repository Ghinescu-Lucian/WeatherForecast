package com.example.weatherapp.data.remote.accuWeather.dto

import com.google.gson.annotations.SerializedName

// Gson inloc de Moshi
data class LocationKeyDto (

    @SerializedName("Key")
    val locationKey: String,

    @SerializedName("LocalizedName")
    val city: String,

    @SerializedName("Country")
    val cuntry: NameDto,

    @SerializedName("AdministrativeArea")
    val administrative: NameDto


)