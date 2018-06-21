package infoandroid.com.newsapplication.restCall

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class RestService {
    companion object {
    private val CONNECTION_TIMEOUT: Long = 600


        fun getService(): NewsAPIService {
            val client = getOkHttpClient()
            return Retrofit.Builder().baseUrl("https://newsapi.org").client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(NewsAPIService::class.java)
        }

        fun getCountryService(): NewsAPIService {
            val client = getOkHttpClient()
            return Retrofit.Builder().baseUrl("https://restcountries.eu").client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(NewsAPIService::class.java)
        }

    private val REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 100)
                .build()
    }



    private fun getOkHttpClient(): OkHttpClient? {
        val okClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
           return okClientBuilder.build()
    }
    }
}