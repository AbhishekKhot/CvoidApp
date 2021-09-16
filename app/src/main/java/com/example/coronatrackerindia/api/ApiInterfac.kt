package com.example.coronatrackerindia.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfac {

    @GET("countries")
    fun listCountries(): Call<List<CountryData>>
}