package com.albertbaron.sherlockrss.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.View
import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.adapters.FeedListAdapter
import com.albertbaron.sherlockrss.helpers.FeedHelper
import com.albertbaron.sherlockrss.models.*
//import com.albertbaron.sherlockrss.layouts.MainActivityUI
import org.jetbrains.anko.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val ui = MainActivityUI()
        //ui.setContentView(this)
        setContentView(R.layout.activity_main)

        val feeds: ArrayList<feed> = getFeedList()

        val f: FeedHelper = FeedHelper()
        async() {
            val al: ArrayList<ArticleList> = f.getAllFeeds(feeds)
            uiThread {
                longToast("feeds updated")
                //val mListView: RecyclerView = ui.holder!!.feedView
                val mListView: RecyclerView =  findViewById(R.id.feedList) as RecyclerView
                mListView.adapter = FeedListAdapter(al, R.layout.feed_list)
                mListView.setHasFixedSize(true);
                mListView.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
                mListView.itemAnimator = DefaultItemAnimator()
            }
        }

        //database.use {
        // }

    }

    fun getFeedList(): ArrayList<feed> {
        val feeds: ArrayList<feed> = ArrayList<feed>()
        feeds.add(feed("xataka", "http://www.xataka.com/index.xml"))
        feeds.add(feed("xatakamovil", "http://www.xatakamovil.com/index.xml"))
        feeds.add(feed("xatakafoto", "http://www.xatakafoto.com/index.xml"))
        feeds.add(feed("xatakandroid", "http://www.xatakandroid.com/index.xml"))
        feeds.add(feed("xatakahome", "http://www.xatakahome.com/index.xml"))
        feeds.add(feed("xatakawindows", "http://www.xatakawindows.com/index.xml"))
        feeds.add(feed("xatakaciencia", "http://www.xatakaciencia.com/index.xml"))
        feeds.add(feed("applesfera", "http://www.applesfera.com/index.xml"))
        feeds.add(feed("vidaextra", "http://www.vidaextra.com/index.xml"))
        feeds.add(feed("genbeta", "http://www.genbeta.com/index.xml"))
        feeds.add(feed("genbetadev", "http://www.genbetadev.com/index.xml"))
        return feeds
    }



}
