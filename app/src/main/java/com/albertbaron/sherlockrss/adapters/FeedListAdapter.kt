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
import com.albertbaron.sherlockrss.models.ArticleList

import java.util.ArrayList

class FeedListAdapter(private val items: ArrayList<ArticleList>, private val itemLayout: Int) : RecyclerView.Adapter<FeedListAdapter.ViewHolder>() {

    var pContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        pContext = parent.context
        val v = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.feedTitle.text = item.Title
        holder.articleList.adapter = ArticleListAdapter(item.Articles, R.layout.article_list)
        holder.articleList.setHasFixedSize(true);
        holder.articleList.layoutManager = LinearLayoutManager(pContext, LinearLayoutManager.HORIZONTAL, false)
        holder.articleList.itemAnimator = DefaultItemAnimator()
        holder.itemView.tag = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var feedTitle: TextView = itemView.findViewById(R.id.feedTitle) as TextView
        var articleList: RecyclerView = itemView.findViewById(R.id.articleList) as RecyclerView
    }

}