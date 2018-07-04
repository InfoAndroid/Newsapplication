package infoandroid.com.newsapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {

    // Access property for Context
    val Context.database: MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(getApplicationContext())

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("News",true,"id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "author" to TEXT,
                "title" to TEXT,
                "description" to TEXT,
                "url" to TEXT,
                "urlToImage" to BLOB)

        db?.createTable(Constantes.TABLE_COUNTRIES,true,"id" to INTEGER + PRIMARY_KEY + UNIQUE,
                Constantes.NAME to TEXT,
                Constantes.ALPHA2CODE to TEXT,
                Constantes.CAPITAL to TEXT,
                Constantes.REGION to TEXT,
                Constantes.SUBREGION to TEXT,
                Constantes.POPULATION to TEXT,
                Constantes.FLAG to BLOB)
           }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("News", true)
       }


}