package com.example.countries.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countries.R
import com.example.countries.model.Country
import kotlinx.android.synthetic.main.card_country.view.*

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.MyHolder>()
{

    private lateinit var context: Context

    fun updateCountries(newCountries:List<Country>)
    {
        countryList.clear()
        countryList.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_country,parent,false)
        context = parent.context
        return MyHolder(view)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_country_name).text = countryList[position].countryName
        Glide.with(context).load(countryList[position].flag).into(holder.itemView.iv_flag)
        holder.itemView.tv_capital_name.text = countryList[position].capital
    }

    class  MyHolder(view : View) : RecyclerView.ViewHolder(view)
}