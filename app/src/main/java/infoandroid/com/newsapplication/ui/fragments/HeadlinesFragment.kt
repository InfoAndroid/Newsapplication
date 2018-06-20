package infoandroid.com.newsapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.adapter.NewsAdapter
import infoandroid.com.newsapplication.models.Article
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.models.NewsModel
import infoandroid.com.newsapplication.restCall.ApiID
import infoandroid.com.newsapplication.restCall.ResponseListener
import infoandroid.com.newsapplication.restCall.RestClass
import infoandroid.com.newsapplication.ui.activitys.MainActivity
import kotlinx.android.synthetic.main.content_main.*

class HeadlinesFragment : Fragment(),ResponseListener {


    var text = ""
    var country = ""
    var newsList = java.util.ArrayList<Article>()
    var countriesList = java.util.ArrayList<ResponseCountries>()
    var newsModel: NewsModel? = null
    lateinit var type:String
    private var restClient: RestClass? = null


    companion object {
        fun newInstance(type: String,country: String): HeadlinesFragment {
            val fragment = HeadlinesFragment()
            val bundle = Bundle()
            bundle.putString("type", type)

            fragment.arguments = bundle

            return fragment
        }


    }
    fun relod(country: String){
        DeasboardApiCall(country)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         text = arguments?.get("type").toString()

        restClient = RestClass(activity!!)
        //DeasboardApiCall(country)
    }

    override fun onResume() {
        super.onResume()

        DeasboardApiCall((activity as MainActivity).countryCode)
    }
    fun DeasboardApiCall(value:String){
        restClient?.callback(this)?.getCountryNews(value)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hedlines_fragmant,container,false)

    }
    private fun setAdapter() {
        if (newsList.size != 0) {
            recylerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            val adapter = NewsAdapter(newsList, activity!!)
            noData.visibility = View.GONE
            recylerView.visibility = View.VISIBLE
            recylerView.adapter = adapter
        } else {
            recylerView.visibility = View.GONE
            noData.visibility = View.VISIBLE
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view.textView.setText("Text")
    }

    override fun onSuccessResponce(apiId: Int, responce: Any) {
        when(apiId) {
            ApiID.NEWA_COUNTRY_API -> {
                newsModel = responce as NewsModel
                newsList = newsModel!!.articles

                setAdapter()
            }
        }
    }

    override fun onFailearResponce(apiId: Int, error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}