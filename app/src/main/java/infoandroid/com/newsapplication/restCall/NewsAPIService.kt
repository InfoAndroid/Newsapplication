package infoandroid.com.newsapplication.restCall

import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.models.NewsModel
import infoandroid.com.newsapplication.restCall.ApiID.Companion.APIKEY_KEY
import infoandroid.com.newsapplication.restCall.ApiID.Companion.CATEGORY_KEY
import infoandroid.com.newsapplication.restCall.ApiID.Companion.COUNTRY_KEY
import infoandroid.com.newsapplication.restCall.ApiID.Companion.SOURCE_KEY

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("/rest/v2/all")
    fun getCVounteresInformation():Call<List<ResponseCountries>>

    @GET("/v1/articles")
    fun getNews(@Query(SOURCE_KEY) source: String,
                @Query(APIKEY_KEY) apiKey: String):Call<NewsModel>

    @GET("/v2/top-headlines")
    fun getCountryNews(@Query(COUNTRY_KEY) source: String,
                       @Query(APIKEY_KEY) apiKey: String):Call<NewsModel>

    @GET("/v2/top-headlines")
    fun getCategoryNews(@Query(COUNTRY_KEY) source: String,
                        @Query(CATEGORY_KEY) category: String,
                        @Query(APIKEY_KEY) apiKey: String):Call<NewsModel>




}