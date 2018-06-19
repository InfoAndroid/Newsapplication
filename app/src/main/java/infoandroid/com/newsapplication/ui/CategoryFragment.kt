package infoandroid.com.newsapplication.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import infoandroid.com.newsapplication.R
import infoandroid.com.newsapplication.adapter.NewsAdapter
import infoandroid.com.newsapplication.models.Article
import infoandroid.com.newsapplication.models.NewsModel
import kotlinx.android.synthetic.main.content_main.*

class CategoryFragment : Fragment() {
    lateinit var newsModel:NewsModel
    var newsList = java.util.ArrayList<Article>()

    companion object {
        fun newInstance(newsModel: NewsModel): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putString("model", newsModel.toString())
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsModel = arguments?.get("model") as NewsModel
        newsList=newsModel.articles
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.category_fragmant,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view.textView.setText("Text")
    }
    private fun setAdapter() {
        if (newsList.size != 0) {
            recylerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            val adapter = NewsAdapter(newsList, activity!!)
            noData.visibility = View.GONE
            recylerView.visibility = View.VISIBLE
            recylerView.adapter = adapter
        } else {
            recylerView.visibility = View.GONE
            noData.visibility = View.VISIBLE
        }

    }

    }