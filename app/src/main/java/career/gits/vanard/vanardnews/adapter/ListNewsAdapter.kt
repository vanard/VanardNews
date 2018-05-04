package career.gits.vanard.vanardnews.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import career.gits.vanard.vanardnews.NewsDetailActivity
import career.gits.vanard.vanardnews.R
import career.gits.vanard.vanardnews.ViewNewsActivity
import career.gits.vanard.vanardnews.`interface`.ItemClickListener
import career.gits.vanard.vanardnews.model.Article
import com.squareup.picasso.Picasso

class ListNewsAdapter(val newsList:MutableList<Article>, private val context: Context): RecyclerView.Adapter<ListNewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val v = inflater.inflate(R.layout.news_layout, parent, false)
        return ListNewsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
        Picasso.with(context)
                .load(newsList[position].urlToImage)
                .into(holder.newsImage)

        if (newsList[position].title!!.length > 63) {
            holder.newsTitle.text = newsList[position].title!!.substring(0, 63) + "..."
        } else {
            holder.newsTitle.text = newsList[position].title!!
        }

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val i = Intent(context, ViewNewsActivity::class.java)
                i.putExtra("webUrl", newsList[position].url)
                i.putExtra("title", newsList[position].title)
                i.putExtra("desc", newsList[position].description)
                i.putExtra("image", newsList[position].description)
                context.startActivity(i)
            }

        })
    }
}

