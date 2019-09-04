package com.example.countries

import com.example.countries.di.DaggerApiComponent
import com.example.countries.model.Country
import io.reactivex.Single
import javax.inject.Inject

class CountryService {

    @Inject
    lateinit var apiInterface: APIInterface

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>>
    {
        return apiInterface.getCountries()
    }
}