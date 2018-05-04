package career.gits.vanard.vanardnews.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import career.gits.vanard.vanardnews.`interface`.ItemClickListener
import kotlinx.android.synthetic.main.source_news_layout.view.*

class NewsViewHolder(mView: View): RecyclerView.ViewHolder(mView), View.OnClickListener{

    private lateinit var itemClickListener: ItemClickListener

    var title = mView.tv_title_news

    init {
        mView.setOnClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!, adapterPosition)
    }
}