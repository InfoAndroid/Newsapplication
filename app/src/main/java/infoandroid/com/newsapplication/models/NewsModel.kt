package infoandroid.com.newsapplication.models

data class NewsModel(
        val status: String,
        val source: String,
        val sortBy: String,
        val logoUrl: String,
        val articles: ArrayList<Article>)