package infoandroid.com.newsapplication.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.adapter.NewsAdapter
import infoandroid.com.newsapplication.models.Article
import infoandroid.com.newsapplication.models.NewsModel
import infoandroid.com.newsapplication.restCall.ApiID
import infoandroid.com.newsapplication.restCall.ResponseListener
import infoandroid.com.newsapplication.restCall.RestClass
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.google.gson.Gson
import infoandroid.com.newsapplication.adapter.CountryAdapter
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import java.util.*


class MainActivity : BaseActivity(), ResponseListener,NavigationView.OnNavigationItemSelectedListener {


     var newsList = java.util.ArrayList<Article>()
     var countriesList = java.util.ArrayList<ResponseCountries>()

    private var restClient: RestClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        restClient = RestClass(this@MainActivity)
        restClient?.callback(this)?.getCountresInformation()
    }

    private fun setAdapter() {
        recylerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = NewsAdapter(newsList, this)
        recylerView.adapter = adapter
    }
    private fun setCountryAdapter() {
        lst_menu_items.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = CountryAdapter(countriesList, this)
        lst_menu_items.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
     when(item.itemId){
         R.id.nav_camera ->{

         }

         R.id.nav_gallery ->{

         }
         R.id.nav_slideshow ->{

         }
         R.id.nav_manage ->{

         }
         R.id.nav_share ->{

         }
         R.id.nav_send ->{

         }
     }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSuccessResponce(apiId: Int, responce: Any) {
        when(apiId){
            ApiID.COUNTRIE_API ->{
                countriesList = responce as ArrayList<ResponseCountries>
                setCountryAdapter()
            }
            ApiID.NEWA_API ->{
                val value = responce as NewsModel
                newsList = value.articles
                setAdapter()
            }
        }

    }

    override fun onFailearResponce(apiId: Int, error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}

