package infoandroid.com.newsapplication.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.ahmadrosid.svgloader.SvgLoader
import infoandroid.com.newsapplication.Constantes
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.models.Article
import infoandroid.com.newsapplication.models.NewsModel
import infoandroid.com.newsapplication.restCall.ApiID
import infoandroid.com.newsapplication.restCall.ResponseListener
import infoandroid.com.newsapplication.restCall.RestClass
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import infoandroid.com.newsapplication.adapter.CountryAdapter
import infoandroid.com.newsapplication.adapter.HeadlinePagerAdapter
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import infoandroid.com.newsapplication.restCall.OnItemClickListener
import infoandroid.com.newsapplication.ui.fragments.CategoryFragment
import infoandroid.com.newsapplication.ui.fragments.HeadlinesFragment
import org.jetbrains.anko.db.*
import kotlin.collections.ArrayList


class MainActivity() : BaseActivity(), ResponseListener,OnItemClickListener {
    var newsList = java.util.ArrayList<Article>()
     var countriesList = java.util.ArrayList<ResponseCountries>()
    var newsModel: NewsModel? = null
    var countryCode:String = "IN"

    private var restClient: RestClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        tabs.setupWithViewPager(pager)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

     database.use {
         select(Constantes.TABLE_COUNTRIES).exec {
            Log.d("ColumCount ${this.columnCount}","count :- ${this.count} ")
         }
        }

          restClient = RestClass(this@MainActivity)
          restClient?.callback(this)?.getCountresInformation()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.action_settings->{
                    val intent =  Intent(this,InfoActivity::class.java)
                    intent.putExtra(Constantes.EXTRA_MESSAGE_DATA, countryCode)
                    startActivity(intent)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setupViewPager(pager)
    }

    private fun setCountryAdapter() {

        database.use {for (value in countriesList){
                        insert(Constantes.TABLE_COUNTRIES,
                        Constantes.NAME to value.name,
                                Constantes.ALPHA2CODE to value.alpha2Code,
                                Constantes.CAPITAL to value.capital,
                                Constantes.REGION to value.region,
                                Constantes.SUBREGION to value.subregion,
                                Constantes.POPULATION to value.population,
                                Constantes.FLAG to value.flag)

        }
        }
        lst_menu_items.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = CountryAdapter(countriesList, this,this)
        lst_menu_items.adapter = adapter
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

    private fun setupViewPager(pager: ViewPager?,value:String="IN") {
        val adapter = HeadlinePagerAdapter(supportFragmentManager)

        val f1 = HeadlinesFragment.newInstance(getString(R.string.headlines),value)
        adapter.addFragment(f1, getString(R.string.headlines))
        val f2 = CategoryFragment.newInstance(getString(R.string.business))
        adapter.addFragment(f2, getString(R.string.business))
        val f3 = CategoryFragment.newInstance(getString(R.string.entertainment))
        adapter.addFragment(f3, getString(R.string.entertainment))
        val f4 = CategoryFragment.newInstance(getString(R.string.Science))
        adapter.addFragment(f4, getString(R.string.Science))
        val f5 = CategoryFragment.newInstance(getString(R.string.Sport))
        adapter.addFragment(f5, getString(R.string.Sport))
        val f6 = CategoryFragment.newInstance(getString(R.string.technology))
        adapter.addFragment(f6, getString(R.string.technology))
        val f7 = CategoryFragment.newInstance(getString(R.string.health))
        adapter.addFragment(f7, getString(R.string.health))
        pager?.adapter = adapter
        }
}

