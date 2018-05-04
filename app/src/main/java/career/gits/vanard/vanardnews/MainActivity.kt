package career.gits.vanard.vanardnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import career.gits.vanard.vanardnews.`interface`.NewsService
import career.gits.vanard.vanardnews.adapter.NewsAdapter
import career.gits.vanard.vanardnews.common.Common
import career.gits.vanard.vanardnews.model.NewsSource
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mService: NewsService
    lateinit var mAdapter: NewsAdapter
    lateinit var dialog: SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paper.init(this)
        mService = Common.newsService
        swipe_refresh.setOnRefreshListener {
            loadNews(true)
        }

        rv.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager

        dialog = SpotsDialog(this)

        loadNews(false)
    }

    private fun loadNews(isRefresh: Boolean) {
        if (!isRefresh)
        {
            val cache = Paper.book().read<String>("cache")
            if (cache != null && !cache.isBlank() && cache != "null")
            {
                val mNews = Gson().fromJson<NewsSource>(cache, NewsSource::class.java)
                mAdapter = NewsAdapter(baseContext, mNews)
                mAdapter.notifyDataSetChanged()
                rv.adapter = mAdapter
            }else{
                dialog.show()

                mService.sources.enqueue(object : retrofit2.Callback<NewsSource>{
                    override fun onFailure(call: Call<NewsSource>?, t: Throwable?) {
                        Toast.makeText(baseContext, "", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<NewsSource>?, response: Response<NewsSource>?) {
                        mAdapter = NewsAdapter(baseContext, response!!.body()!!)
                        mAdapter.notifyDataSetChanged()
                        rv.adapter = mAdapter

                        Paper.book().write("cache", Gson().toJson(response!!.body()!!))

                        dialog.dismiss()
                    }
                })
            }
        }else{
            swipe_refresh.isRefreshing = true

            mService.sources.enqueue(object : retrofit2.Callback<NewsSource>{
                override fun onFailure(call: Call<NewsSource>?, t: Throwable?) {
                    Toast.makeText(baseContext, "", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<NewsSource>?, response: Response<NewsSource>?) {
                    mAdapter = NewsAdapter(baseContext, response!!.body()!!)
                    mAdapter.notifyDataSetChanged()
                    rv.adapter = mAdapter

                    Paper.book().write("cache", Gson().toJson(response!!.body()!!))

                    swipe_refresh.isRefreshing = false
                }
            })
        }
    }
}
