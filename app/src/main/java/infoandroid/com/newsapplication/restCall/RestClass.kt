package infoandroid.com.newsapplication.restCall

import android.content.Context
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.models.NewsModel
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestClass (context: Context) {
    val dialog = context.indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")
        lateinit var responseListener : ResponseListener
        private lateinit var newsAPIService : NewsAPIService
var context : Context=context
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

       dialog.show()
        val call = getApi().getNews("associated-press","bff73f585b2d4936bad89b8b887e500b")

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {
                dialog.hide()
                if (response != null) {
                    responseListener.onSuccessResponce(ApiID.NEWA_API, response.body()!!)
                }
            }



            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                dialog.hide()
                responseListener.onFailearResponce(ApiID.NEWA_API, t.message!!)
            }

        })
    }
    fun getCountresInformation (){
        dialog.show()
        val call = getApiCountre().getCVounteresInformation()
        call.enqueue(object :Callback<List<ResponseCountries>>{
            override fun onFailure(call: Call<List<ResponseCountries>>?, t: Throwable?) {
                dialog.hide()
                responseListener.onFailearResponce(ApiID.COUNTRIE_API, t?.message!!)
            }

            override fun onResponse(call: Call<List<ResponseCountries>>?, response: Response<List<ResponseCountries>>?) {
                dialog.hide()
                responseListener.onSuccessResponce(ApiID.COUNTRIE_API,response?.body()!!)
            }
        })
    }

    fun getCountryNews(alpha2Code: String) {
        dialog.show()
        val call = getApi().getCountryNews(alpha2Code,"bff73f585b2d4936bad89b8b887e500b")

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {
               dialog.hide()
                if (response != null) {
                    responseListener.onSuccessResponce(ApiID.NEWA_COUNTRY_API, response.body()!!)
                }
            }



            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
               dialog.hide()
                responseListener.onFailearResponce(ApiID.NEWA_COUNTRY_API, t.message!!)
            }

        })
    }

    fun getCategoryNews(alpha2Code: String,category: String) {
        dialog.show()
        val call = getApi().getCategoryNews(alpha2Code,category,"bff73f585b2d4936bad89b8b887e500b")

        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {
                dialog.hide()
                if (response != null) {
                    responseListener.onSuccessResponce(ApiID.NEWA_COUNTRY_API, response.body()!!)
                }
            }



            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
               dialog.hide()
                responseListener.onFailearResponce(ApiID.NEWA_COUNTRY_API, t.message!!)
            }

        })
    }

}