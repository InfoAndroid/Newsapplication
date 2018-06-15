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
        /*if (Util.isNetworkAvailable(ApplicationClass.getInstance())) {
            int maxAge = 60; // read from cache for 1 minute
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }*/

        originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 100)
                .build()
    }



    private fun getOkHttpClient(): OkHttpClient? {
        val okClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        /*okClientBuilder.addInterceptor(httpLoggingInterceptor)
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

        okClientBuilder.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR)
*/

        return okClientBuilder.build()
    }
    }
}