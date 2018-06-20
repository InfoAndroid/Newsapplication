package infoandroid.com.newsapplication.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.models.Article
import infoandroid.com.newsapplication.ui.activitys.WebViewActivity
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
        holder.newsTitle.text=items[position].title

       Picasso.with(context).load(items[position].urlToImage).error(R.mipmap.ic_launcher).into(holder.newsImage);

        holder.view.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url",items[position].url )
            context.startActivity(intent)
        })


    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val newsText = itemView.tvNews
    val newsTitle= itemView.tvTitleNews
    val newsImage= itemView.newsimge
    val view=itemView
    }
}