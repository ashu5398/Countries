package com.example.countries

import com.example.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries() : Single<List<Country>>

}