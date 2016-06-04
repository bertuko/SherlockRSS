package com.albertbaron.sherlockrss.adapters

import android.support.v7.widget.RecyclerView
import android.view.*
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
        holder.articleDate.text = item.Author!!.toString()
        holder.itemView.tag = items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        var articleTitle: TextView = itemView.findViewById(R.id.articleTitle) as TextView
        var articleDate: TextView = itemView.findViewById(R.id.articleDate) as TextView

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
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




