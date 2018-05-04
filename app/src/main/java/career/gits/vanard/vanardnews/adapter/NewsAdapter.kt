package career.gits.vanard.vanardnews.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import career.gits.vanard.vanardnews.ListNewsActivity
import career.gits.vanard.vanardnews.R
import career.gits.vanard.vanardnews.`interface`.ItemClickListener
import career.gits.vanard.vanardnews.model.NewsSource

class NewsAdapter(private val context: Context, private val mNewsSource: NewsSource): RecyclerView.Adapter<NewsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.source_news_layout, parent, false)
        return NewsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mNewsSource.sources!!.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder!!.title.text = mNewsSource.sources!![position].name

        holder.setItemClickListener(object : ItemClickListener
        {
            override fun onClick(view: View, position: Int) {
                val i = Intent(context, ListNewsActivity::class.java)
                i.putExtra("source", mNewsSource.sources!![position].id)
                context.startActivity(i)
            }
        })
    }

}