package infoandroid.com.newsapplication.ui.activitys

import android.content.Context
import android.support.v7.app.AppCompatActivity
import infoandroid.com.newsapplication.MyDatabaseOpenHelper

open class BaseActivity : AppCompatActivity() {

  val Context.database: MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(getApplicationContext())

}