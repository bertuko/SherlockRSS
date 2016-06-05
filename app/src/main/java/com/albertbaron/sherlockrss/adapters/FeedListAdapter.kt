package com.albertbaron.sherlockrss.adapters

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.activities.ArticleActivity
import com.albertbaron.sherlockrss.models.Article
import com.albertbaron.sherlockrss.models.ArticleList
import java.util.*
import org.jetbrains.anko.*
import org.parceler.Parcels

class FeedListAdapter(private val items: List<ArticleList>, private val itemLayout: Int) : RecyclerView.Adapter<FeedListAdapter.ViewHolder>() {

    var pContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        pContext = parent.context
        val v = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.feedTitle.text = item.Title
        val adapt: ArticleListAdapter = ArticleListAdapter(item.Articles, R.layout.article_list)
        adapt.setOnItemClickListener(ClickListenerImpl())
        holder.articleList.adapter = adapt
        holder.articleList.setHasFixedSize(true);
        holder.articleList.layoutManager = LinearLayoutManager(pContext, LinearLayoutManager.HORIZONTAL, false)
        holder.articleList.itemAnimator = DefaultItemAnimator()
        holder.itemView.tag = items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var feedTitle: TextView = itemView.findViewById(R.id.feedTitle) as TextView
        var articleList: RecyclerView = itemView.findViewById(R.id.articleList) as RecyclerView
    }

    class ClickListenerImpl : ArticleListAdapter.ClickListener {

        override fun onItemClick(position: Int, v: View) {
            val items = v.tag as ArrayList<Article>
            v.context.startActivity<ArticleActivity>(
                    "Article" to Parcels.wrap(items),
                    "Position" to position
            )
        }

        override fun onItemLongClick(position: Int, v: View) {
            //val item = v.tag as Article
            //v.context.startActivity<ArticleActivity>("Article" to item)
        }
    }

}