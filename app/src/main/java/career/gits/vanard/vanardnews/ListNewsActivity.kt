package career.gits.vanard.vanardnews

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import career.gits.vanard.vanardnews.R.id.*
import career.gits.vanard.vanardnews.`interface`.NewsService
import career.gits.vanard.vanardnews.adapter.ListNewsAdapter
import career.gits.vanard.vanardnews.common.Common
import career.gits.vanard.vanardnews.model.News
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_list_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNewsActivity : AppCompatActivity() {

    var source=""
    var webUrl:String?=""
    var titleNews:String?=""
    var descrip:String?=""
    var urlToImage:String?=""

    lateinit var dialog: SpotsDialog
    lateinit var mService: NewsService
    lateinit var mAdapter: ListNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        mService = Common.newsService
        dialog = SpotsDialog(this)

        list_swipe_refresh.setOnRefreshListener { loadNews(source, true) }

        dg_layout.setOnClickListener {
            val i = Intent(baseContext, ViewNewsActivity::class.java)
            i.putExtra("webUrl", webUrl)
            i.putExtra("title", titleNews)
            i.putExtra("desc", descrip)
            i.putExtra("image", urlToImage)
            startActivity(i)
        }

        rv_news.setHasFixedSize(true)
        rv_news.layoutManager = LinearLayoutManager(this)

        if (intent != null)
        {
            source = intent.getStringExtra("source")
            if (!source.isEmpty())
                loadNews(source, false)
        }
    }

    private fun loadNews(source: String, isRefresh: Boolean) {
        if (isRefresh){
            dialog.show()
            mService.getNews(Common.getNewsHeadlines(source!!))
                    .enqueue(object : Callback<News> {
                        override fun onFailure(call: Call<News>?, t: Throwable?) {

                        }

                        override fun onResponse(call: Call<News>?, response: Response<News>?) {
                            dialog.dismiss()

                            Picasso.with(baseContext)
                                    .load(response!!.body()!!.articles!![0].urlToImage)
                                    .into(head_image)

                            head_title.text = response!!.body()!!.articles!![0].title
                            head_author.text = response!!.body()!!.articles!![0].author

                            webUrl = response!!.body()!!.articles!![0].url

                            val removeFirstItem = response!!.body()!!.articles
                            removeFirstItem!!.removeAt(0)

                            mAdapter = ListNewsAdapter(removeFirstItem!!, baseContext)
                            mAdapter.notifyDataSetChanged()
                            rv_news.adapter = mAdapter
                        }

                    })
        }else{
            dialog.show()

            list_swipe_refresh.isRefreshing = true
            mService.getNews(Common.getNewsHeadlines(source!!))
                    .enqueue(object : Callback<News> {
                        override fun onFailure(call: Call<News>?, t: Throwable?) {

                        }

                        override fun onResponse(call: Call<News>?, response: Response<News>?) {
                            list_swipe_refresh.isRefreshing = false

                            dialog.dismiss()

                            Picasso.with(baseContext)
                                    .load(response!!.body()!!.articles!![0].urlToImage)
                                    .into(head_image)

                            head_title.text = response!!.body()!!.articles!![0].title
                            head_author.text = response!!.body()!!.articles!![0].author

                            webUrl = response!!.body()!!.articles!![0].url

                            val removeFirstItem = response!!.body()!!.articles
                            removeFirstItem!!.removeAt(0)

                            mAdapter = ListNewsAdapter(removeFirstItem!!, baseContext)
                            mAdapter.notifyDataSetChanged()
                            rv_news.adapter = mAdapter
                        }

                    })
        }
    }
}
