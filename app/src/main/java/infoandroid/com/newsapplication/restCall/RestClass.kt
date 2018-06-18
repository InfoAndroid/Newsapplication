package infoandroid.com.newsapplication.restCall

import android.content.Context
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.models.NewsModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestClass (context: Context) {
        lateinit var responseListener : ResponseListener
        private lateinit var newsAPIService : NewsAPIService

    fun callback(responseListener: ResponseListener): RestClass {
        this.responseListener = responseListener
        return this
    }

      fun getApi(): NewsAPIService {
              newsAPIService = RestService.getService()

         return newsAPIService
     }

    fun getApiCountre(): NewsAPIService {
        newsAPIService = RestService.getCountryService()

        return newsAPIService
    }

    fun getNews(count: Int) {
        val call = getApi().getNews("associated-press","bff73f585b2d4936bad89b8b887e500b")

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {
                if (response != null) {
                    responseListener.onSuccessResponce(ApiID.NEWA_API, response.body()!!)
                }
            }



            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                responseListener.onFailearResponce(ApiID.NEWA_API, t.message!!)
            }

        })
    }
    fun getCountresInformation (){
        val call = getApiCountre().getCVounteresInformation()
        call.enqueue(object :Callback<List<ResponseCountries>>{
            override fun onFailure(call: Call<List<ResponseCountries>>?, t: Throwable?) {
                responseListener.onFailearResponce(ApiID.COUNTRIE_API, t?.message!!)
            }

            override fun onResponse(call: Call<List<ResponseCountries>>?, response: Response<List<ResponseCountries>>?) {
                responseListener.onSuccessResponce(ApiID.COUNTRIE_API,response?.body()!!)
            }
        })
    }

    fun getCountryNews(alpha2Code: String) {
        val call = getApi().getCountryNews(alpha2Code,"bff73f585b2d4936bad89b8b887e500b")

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {
                if (response != null) {
                    responseListener.onSuccessResponce(ApiID.NEWA_COUNTRY_API, response.body()!!)
                }
            }



            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                responseListener.onFailearResponce(ApiID.NEWA_COUNTRY_API, t.message!!)
            }

        })
    }

}