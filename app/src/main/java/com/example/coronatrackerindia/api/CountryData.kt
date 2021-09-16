package com.example.coronatrackerindia.api

data class CountryData(
    val updated: Long,
    val country: String,
    val cases: String,
    val todayCases: String,
    val deaths: String,
    val todayDeaths: String,
    val recovered: String,
    val todayRecovered: String,
    val active: String,
    val tests: String
)