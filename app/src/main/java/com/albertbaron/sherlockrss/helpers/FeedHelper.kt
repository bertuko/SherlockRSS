package com.albertbaron.sherlockrss.helpers

import com.albertbaron.sherlockrss.models.*
import com.einmalfel.earl.*
import org.jetbrains.anko.async
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class FeedHelper  {
    private val poolTimeOut: Long = 10
    private val poolTimeUnit: TimeUnit = TimeUnit.SECONDS

    fun getSingleFeed(link: String): ArticleList? {
        var al: ArticleList? = null
        val pool = Executors.newFixedThreadPool(1)
        async(pool) {
            val result = getFeedXML(link)
            if (result != null) al = getFeedFromStream(result)
        }
        pool.shutdown()
        pool.awaitTermination(poolTimeOut, poolTimeUnit)
        return al
    }

    fun getAllFeeds(links: ArrayList<FeedInfo>): ArrayList<ArticleList> {
        val lArticleList : ArrayList<ArticleList> = ArrayList<ArticleList>(links.count())
        val pool = Executors.newFixedThreadPool(links.count())
        for (url in links) {
            async(pool) {
                val result = getFeedXML(url.feedUrl)
                if (result != null) lArticleList.add(getFeedFromStream(result))
            }
        }
        pool.shutdown()
        pool.awaitTermination(poolTimeOut, poolTimeUnit)
        return lArticleList
    }

    private fun getFeedXML(link: String): InputStream? {
        val httpClient = URL(link).openConnection() as HttpURLConnection
        if(httpClient.responseCode == HttpURLConnection.HTTP_OK){
            return httpClient.inputStream
        }
        return null
    }

    private fun getFeedFromStream(inStr: InputStream): ArticleList {
        val f: Feed =  EarlParser.parseOrThrow(inStr,25)
        return processFeed(f)
    }

    private fun processFeed (f: Feed): ArticleList{
        val al: ArrayList<Article> = ArrayList<Article>(f.items.count())
        f.items.forEach { i ->
            val a: Article = Article (
                    Title = i.title ?: "",
                    Author = i.author ?: "",
                    Description = i.description ?: "",
                    Link = i.link ?: "",
                    ImageLink = i.imageLink ?: "",
                    publicationDate = i.publicationDate
            )
            al.add(a)
        }

        val artList: ArticleList = ArticleList(
                f.title,
                f.author ?: "",
                f.description ?: "",
                f.link ?: "",
                f.imageLink ?: "",
                al
        )

        return artList
    }

}