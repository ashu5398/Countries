 package com.example.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import com.example.countries.model.Country
import com.example.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {

     lateinit var viewModel : ListViewModel
     private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_countries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
            itemAnimator = DefaultItemAnimator()
        }

        observeViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }


    }

     private fun observeViewModel() {

         viewModel.countries.observe(this, Observer {countries->countries?.let { println(it)
             rv_countries.visibility = View.VISIBLE
             countryAdapter.updateCountries(it)}})

         viewModel.countryLoadError.observe(this, Observer {  loadingError.visibility = if(it) View.VISIBLE else View.GONE})
         viewModel.loading.observe(this, Observer {  loadingBar.visibility = if(it) View.VISIBLE else View.GONE
            if(it)
            {
                rv_countries.visibility = View.GONE
                loadingError.visibility = View.GONE
            }
         })

     }
 }
