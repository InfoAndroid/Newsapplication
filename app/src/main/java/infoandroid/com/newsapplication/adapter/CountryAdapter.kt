package infoandroid.com.newsapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import infoandroid.com.newsapplication.R

import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import kotlinx.android.synthetic.main.row_layout_country.view.*
import android.net.Proxy.getHost
import java.net.URI
import java.net.URL
import android.graphics.BitmapFactory




class CountryAdapter (val items : ArrayList<ResponseCountries>, val context: Context) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_layout_country,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text=items[position].name
        holder.tvdesc.text=items[position].nativeName



        Picasso.with(context).load(items[position].flag).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image);


    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val tvName = itemView.tvName
    val tvdesc = itemView.tvdesc
    val image = itemView.imgFlag
    }
}