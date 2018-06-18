package infoandroid.com.newsapplication.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import infoandroid.com.newsapplication.R

import infoandroid.com.newsapplication.models.CountriesResponce.ResponseCountries
import kotlinx.android.synthetic.main.row_layout_country.view.*
import com.ahmadrosid.svgloader.SvgLoader
import infoandroid.com.newsapplication.restCall.OnItemClickListener


class CountryAdapter (val items : ArrayList<ResponseCountries>, val context: Context,listener:OnItemClickListener) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

     val mListener: OnItemClickListener? = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_layout_country,parent,false)
        return ViewHolder(v, this.mListener!!)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text=items[position].name
        holder.tvdesc.text=items[position].nativeName

        SvgLoader.pluck()
                .with(context as Activity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(items[position].flag, holder.image);

        holder.view.setOnClickListener(View.OnClickListener {
            mListener?.onItemClick(holder.image,position)
        })

    }


    class ViewHolder(itemView: View ,listener:OnItemClickListener):RecyclerView.ViewHolder(itemView){
        val view : View = itemView
        val tvName = itemView.tvName
        val tvdesc = itemView.tvdesc
        val image = itemView.imgFlag

    }


}