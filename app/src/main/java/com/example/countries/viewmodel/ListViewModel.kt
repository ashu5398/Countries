package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.CountryService
import com.example.countries.di.DaggerApiComponent
import com.example.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var countryService : CountryService

    val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh()
    {
        fetchCountries()
    }

    fun fetchCountries()
    {
        loading.value = true
        disposable.add(
            countryService.getCountries().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>()
                {
                    override fun onSuccess(value: List<Country>?) {
                        loading.value = false
                        countryLoadError.value = false
                        countries.value = value
                    }

                    override fun onError(e: Throwable?) {
                        loading.value = false
                        countryLoadError.value = true
                    }

                })
        )
    }
}