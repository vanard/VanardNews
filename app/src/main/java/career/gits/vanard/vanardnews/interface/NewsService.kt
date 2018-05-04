package career.gits.vanard.vanardnews.`interface`

import career.gits.vanard.vanardnews.model.News
import career.gits.vanard.vanardnews.model.NewsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {

    @get:GET("v2/sources?apiKey=4eebf74e007c4829b93ee4133756e0d5")
    val sources: Call<NewsSource>

    @GET
    fun getNews(@Url url:String): Call<News>

}