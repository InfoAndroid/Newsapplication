package infoandroid.com.newsapplication.ui.activitys

import android.app.Activity
import android.os.Bundle
import android.provider.AlarmClock
import android.support.annotation.UiThread
import android.util.Log
import com.ahmadrosid.svgloader.SvgLoader
import infoandroid.com.newsapplication.Constantes
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import kotlinx.android.synthetic.main.activity_info.*

import org.jetbrains.anko.db.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class InfoActivity : BaseActivity() {
    var ss:String = ""
    var countriesModel = ResponseCountries()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
         ss = intent.getStringExtra(Constantes.EXTRA_MESSAGE_DATA)
        getcountryDetail()
    }
    fun getcountryDetail(){
        doAsync {
            database.use {
                select(Constantes.TABLE_COUNTRIES).whereArgs("(${Constantes.ALPHA2CODE} = {xyz})", "xyz" to ss).limit(1).exec {
                    this.moveToFirst()
                    countriesModel.name=this.getString(1)
                    countriesModel.alpha2Code=this.getString(2)
                    countriesModel.capital=this.getString(3)
                    countriesModel.region=this.getString(4)
                    countriesModel.subregion=this.getString(5)
                    countriesModel.population=this.getDouble(6)
                    countriesModel.flag=this.getString(7)
                }
            }
           uiThread {
               name.setText("Name :- ${ countriesModel.name}")
                SvgLoader.pluck()
                        .with(this@InfoActivity)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load( countriesModel.flag, flag)
               alphaCode.setText(countriesModel.alpha2Code)
               region.setText("Region :- ${countriesModel.region}")
               Subregion.setText("Subregion :- ${countriesModel.subregion}")
                population.setText("Population :- ${countriesModel.population}")
               capital.setText("Capital :- ${countriesModel.capital}")
           }

        }

    }
}
