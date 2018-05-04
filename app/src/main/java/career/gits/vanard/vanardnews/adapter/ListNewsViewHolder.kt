package career.gits.vanard.vanardnews.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import career.gits.vanard.vanardnews.`interface`.ItemClickListener
import kotlinx.android.synthetic.main.news_layout.view.*

class ListNewsViewHolder(mView: View): RecyclerView.ViewHolder(mView), View.OnClickListener{
    private lateinit var itemClickListener: ItemClickListener

    var newsImage = mView.news_image
    var newsTitle = mView.news_title

    init {
        mView.setOnClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener)
    {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View) {
        itemClickListener.onClick(v, adapterPosition)
    }
}