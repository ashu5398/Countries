package com.example.countries.di

import com.example.countries.APIInterface
import com.example.countries.CountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountryApi(): APIInterface
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    @Provides
    fun provideCountryService(): CountryService
    {
        return CountryService()
    }



}