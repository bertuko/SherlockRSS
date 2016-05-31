package com.albertbaron.sherlockrss.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.models.Article

import java.util.ArrayList

class ArticleListAdapter(private val items: ArrayList<Article>, private val itemLayout: Int) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.articleTitle.text = item.Title
        holder.articleDate.text = item.publicationDate!!.toString()
        holder.itemView.tag = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleTitle: TextView = itemView.findViewById(R.id.articleTitle) as TextView
        var articleDate: TextView = itemView.findViewById(R.id.articleDate) as TextView
    }

}