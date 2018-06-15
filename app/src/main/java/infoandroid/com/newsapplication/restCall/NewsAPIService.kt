package infoandroid.com.newsapplication.restCall

import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.models.NewsModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("/rest/v2/all")
    fun getCVounteresInformation():Call<List<ResponseCountries>>

@GET("/v1/articles")
    fun getNews(@Query("source") source: String,
                @Query("apiKey") apiKey: String):Call<NewsModel>



}