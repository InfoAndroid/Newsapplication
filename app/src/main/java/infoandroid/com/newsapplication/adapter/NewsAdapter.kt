package infoandroid.com.newsapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.models.Article
import kotlinx.android.synthetic.main.row_layout.view.*

class NewsAdapter (val items : ArrayList<Article>, val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsText.text=items[position].description
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val newsText = itemView.tvNews
    }
}