package infoandroid.com.newsapplication.restCall

interface ResponseListener {


    fun onSuccessResponce(apiId: Int, responce: Any)

     fun onFailearResponce(apiId: Int, error: String)
}