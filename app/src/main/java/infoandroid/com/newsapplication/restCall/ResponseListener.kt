package infoandroid.com.newsapplication.restCall

import android.util.Log

interface ResponseListener {


    fun onSuccessResponce(apiId: Int, responce: Any)

     fun onFailearResponce(apiId: Int, error: String)


}