package com.albertbaron.sherlockrss.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.View
import android.widget.ProgressBar
import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.adapters.FeedListAdapter
import com.albertbaron.sherlockrss.helpers.FeedHelper
import com.albertbaron.sherlockrss.models.*
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.processInTransactionAsync
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction
//import com.albertbaron.sherlockrss.layouts.MainActivityUI
import org.jetbrains.anko.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val ui = MainActivityUI()
        //ui.setContentView(this)
        setContentView(R.layout.activity_main)
        val mProgress : ProgressBar = findViewById(R.id.progressBar) as ProgressBar
        mProgress.visibility = View.INVISIBLE
        val f: FeedHelper = FeedHelper()
        fillMainActivity(f.getAllFeeds(getFeedList()))
    }

    fun fillMainActivity (al: List<ArticleList>) {
        //longToast("feeds updated")
        //val mListView: RecyclerView = ui.holder!!.feedView
        val mListView: RecyclerView =  findViewById(R.id.feedList) as RecyclerView
        mListView.adapter = FeedListAdapter(al, R.layout.feed_list)
        mListView.setHasFixedSize(true);
        mListView.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        mListView.itemAnimator = DefaultItemAnimator()
    }

    fun getFeedList(): ArrayList<FeedInfo> {
        val feeds: MutableList<FeedInfo> = SQLite.select()
                .from(FeedInfo::class.java)
                .orderBy(FeedInfo_Table.id, true)
                .queryList()

        if (feeds.count() == 0){
            feeds.add(FeedInfo("xataka", "http://www.xataka.com/index.xml"))
            feeds.add(FeedInfo("xatakamovil", "http://www.xatakamovil.com/index.xml"))
            feeds.add(FeedInfo("xatakafoto", "http://www.xatakafoto.com/index.xml"))
            feeds.add(FeedInfo("xatakandroid", "http://www.xatakandroid.com/index.xml"))
            feeds.add(FeedInfo("xatakahome", "http://www.xatakahome.com/index.xml"))
            feeds.add(FeedInfo("xatakawindows", "http://www.xatakawindows.com/index.xml"))
            feeds.add(FeedInfo("xatakaciencia", "http://www.xatakaciencia.com/index.xml"))
            feeds.add(FeedInfo("applesfera", "http://www.applesfera.com/index.xml"))
            feeds.add(FeedInfo("vidaextra", "http://www.vidaextra.com/index.xml"))
            feeds.add(FeedInfo("genbeta", "http://www.genbeta.com/index.xml"))
            feeds.add(FeedInfo("genbetadev", "http://www.genbetadev.com/index.xml"))

            feeds.processInTransactionAsync({ it, databaseWrapper -> it.save(databaseWrapper) }
                    /*,Transaction.Success {
                        longToast("feeds saved")
                    }
                    ,Transaction.Error {transaction, throwable ->
                        longToast("feeds save error")
                    }*/
            )
        }

        return feeds as ArrayList<FeedInfo>
    }

}
