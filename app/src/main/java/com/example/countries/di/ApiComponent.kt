package com.example.countries.di

import com.example.countries.CountryService
import com.example.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service:CountryService)

    fun inject(viewModel:ListViewModel)

}