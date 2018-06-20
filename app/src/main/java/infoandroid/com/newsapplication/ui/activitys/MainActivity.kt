package infoandroid.com.newsapplication.ui.activitys


import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.ahmadrosid.svgloader.SvgLoader
import infoandroid.com.newsapplication.PreferenceHelper.defaultPrefs
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
import infoandroid.com.newsapplication.adapter.CountryAdapter
import infoandroid.com.newsapplication.adapter.HeadlinePagerAdapter
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.restCall.OnItemClickListener
import infoandroid.com.newsapplication.ui.fragments.CategoryFragment
import infoandroid.com.newsapplication.ui.fragments.HeadlinesFragment
import org.jetbrains.anko.progressDialog
import java.util.*


class MainActivity : BaseActivity(), ResponseListener,NavigationView.OnNavigationItemSelectedListener,OnItemClickListener {


    var newsList = java.util.ArrayList<Article>()
     var countriesList = java.util.ArrayList<ResponseCountries>()
    var newsModel: NewsModel? = null
   public var countryCode:String = "IN"

   // val dialog = progressDialog(message = "Please wait a bit…", title = "Fetching data")
    private var restClient: RestClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)



        tabs.setupWithViewPager(pager)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);


       restClient = RestClass(this@MainActivity)
          restClient?.callback(this)?.getCountresInformation()

        // DeasboardApiCall()
    }

    override fun onResume() {
        super.onResume()
        setupViewPager(pager)
    }

    private fun setAdapter() {
        if (newsList.size!=0){
            recylerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            val adapter = NewsAdapter(newsList, this)
            noData.visibility=View.GONE
            recylerView.visibility=View.VISIBLE
            recylerView.adapter = adapter
        }else{
            recylerView.visibility=View.GONE
            noData.visibility=View.VISIBLE
        }


    }
    private fun setCountryAdapter() {
        lst_menu_items.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = CountryAdapter(countriesList, this,this)
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
                newsModel = responce as NewsModel
                newsList = newsModel!!.articles
                //setAdapter()
            }ApiID.NEWA_COUNTRY_API ->{
                 newsModel = responce as NewsModel
                newsList = newsModel!!.articles

              //  setAdapter()
            }
        }

    }

    override fun onFailearResponce(apiId: Int, error: String) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        SvgLoader.pluck().close();
    }

    override fun onItemClick(view: View, position: Int) {

        countryCode=countriesList[position].alpha2Code!!
        drawer_layout.closeDrawer(GravityCompat.START)
       setupViewPager(pager,countriesList[position].alpha2Code!!)
    }
    fun DeasboardApiCall(value:String="IN"){
        restClient?.callback(this)?.getCountryNews(value)
    }
    private fun setupViewPager(pager: ViewPager?,value:String="IN") {
        val adapter = HeadlinePagerAdapter(supportFragmentManager)

        val f1 = HeadlinesFragment.newInstance("HeadlinesFragment",value)
        adapter.addFragment(f1, "Headlines")

        val f2 = CategoryFragment.newInstance("Business")
        adapter.addFragment(f2, "Business")
        val f3 = CategoryFragment.newInstance("Entertainment")
        adapter.addFragment(f3, "Entertainment")
        val f4 = CategoryFragment.newInstance("Science")
        adapter.addFragment(f4, "Science")


        val f5 = CategoryFragment.newInstance("Sports")
        adapter.addFragment(f5, "Sports")

        val f6 = CategoryFragment.newInstance("Technology")
        adapter.addFragment(f6, "Technology")
        val f7 = CategoryFragment.newInstance("Health")
        adapter.addFragment(f7, "Health")


        pager?.adapter = adapter


        }





}

