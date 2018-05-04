package career.gits.vanard.vanardnews.common

import career.gits.vanard.vanardnews.`interface`.NewsService
import career.gits.vanard.vanardnews.remote.RetrofitClient

object Common {
    val URL = "https://newsapi.org/"
    var KEY = "4eebf74e007c4829b93ee4133756e0d5"

    val newsService: NewsService
        get() = RetrofitClient.getClient(URL).create(NewsService::class.java)

    fun getNewsHeadlines(source:String):String{
        val apiUrl = StringBuilder("https://newsapi.org/v2/top-headlines?sources=")
                .append(source)
                .append("&apiKey=")
                .append(KEY)
                .toString()
        return apiUrl
    }
}