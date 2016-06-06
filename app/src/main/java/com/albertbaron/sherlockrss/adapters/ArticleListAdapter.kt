package com.albertbaron.sherlockrss.adapters

import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.albertbaron.sherlockrss.layouts.ArticleListUI
import com.albertbaron.sherlockrss.models.Article

import java.util.ArrayList

class ArticleListAdapter(private val items: ArrayList<Article>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ALUI = ArticleListUI(parent.context)
        return ViewHolder(ALUI)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.articleTitle.text = item.Title
        holder.articleDate.text = item.publicationDate.toString()
        holder.itemView.tag = items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: ArticleListUI) : RecyclerView.ViewHolder(itemView.v), View.OnClickListener, View.OnLongClickListener {

        var articleTitle: TextView = itemView.t1!!
        var articleDate: TextView = itemView.t2!!

        init {
            itemView.v!!.setOnClickListener(this)
            itemView.v!!.setOnLongClickListener(this)
        }

        override fun onClick(v: View) {
            clickListener!!.onItemClick(adapterPosition, v)
        }

        override fun onLongClick(v: View): Boolean {
            clickListener!!.onItemLongClick(adapterPosition, v)
            return false
        }
    }

    companion object {
        private var clickListener: ArticleListAdapter.ClickListener? = null
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        ArticleListAdapter.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View)
        fun onItemLongClick(position: Int, v: View)
    }

}




