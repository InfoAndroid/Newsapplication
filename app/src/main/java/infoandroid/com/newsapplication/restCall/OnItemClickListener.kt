package infoandroid.com.newsapplication.restCall

import android.view.View
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries

interface OnItemClickListener {
    fun onItemClick(view: View,  position : Int)
}