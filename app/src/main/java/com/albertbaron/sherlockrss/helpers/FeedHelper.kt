package com.albertbaron.sherlockrss.helpers

import com.albertbaron.sherlockrss.models.*
import com.einmalfel.earl.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class FeedHelper  {

    fun getSingleFeed(link: String): ArticleList? {
        val inStr = getFeedXML(link)
        if (inStr != null) {
            return getFeedFromStream(inStr)
        }
        return null
    }

    fun getAllFeeds(links: ArrayList<feed>): ArrayList<ArticleList> {
        val inStr: ArrayList<InputStream> = ArrayList<InputStream>(links.count())
        links.forEach { i ->
            val result = getFeedXML(i.feedUrl)
            if (result != null) inStr.add(result)
        }
        val lArticleList : ArrayList<ArticleList> = ArrayList<ArticleList>(links.count())
        inStr.forEach { i ->
            lArticleList.add(getFeedFromStream(i))
        }
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