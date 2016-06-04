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

    fun getAllFeeds(links: ArrayList<FeedInfo>): ArrayList<ArticleList> {
        val lArticleList : ArrayList<ArticleList> = ArrayList<ArticleList>(links.count())
        val pool = Executors.newFixedThreadPool(links.count())
        for (url in links) {
            async(pool) {
                val result = getFeedXML(url.feedUrl)
                if (result != null) lArticleList.add(getFeedFromStream(result, url.id))
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

    private fun getFeedFromStream(inStr: InputStream, FeedInfoId: Long): ArticleList {
        val f: Feed =  EarlParser.parseOrThrow(inStr,25)
        return processFeed(f, FeedInfoId)
    }

    private fun processFeed (f: Feed, FeedInfoId: Long): ArticleList{
        val al: ArrayList<Article> = ArrayList<Article>(f.items.count())
        f.items.forEach { i ->
            val a: Article = Article (
                    cFeedInfoId = FeedInfoId,
                    cTitle = i.title ?: "",
                    cAuthor = i.author ?: "",
                    cDescription = i.description ?: "",
                    cLink = i.link ?: "",
                    cImageLink = i.imageLink ?: "",
                    cpublicationDate = i.publicationDate
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