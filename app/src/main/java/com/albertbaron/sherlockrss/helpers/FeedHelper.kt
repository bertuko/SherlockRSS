package com.albertbaron.sherlockrss.helpers

import com.albertbaron.sherlockrss.models.*
import com.einmalfel.earl.*
import java.net.URL
import java.util.*

class FeedHelper  {

    fun getAllFeeds(links: ArrayList<feed>): ArrayList<ArticleList> {
        val lArticleList : ArrayList<ArticleList> = ArrayList<ArticleList>(links.count())
        links.forEach { i ->
            val al: ArticleList = getSingleFeed(i.feedUrl)
            lArticleList.add(al)
        }
        return lArticleList
    }

    fun getSingleFeed(link: String): ArticleList {

        val inputStream = URL(link).openConnection().inputStream
        val f: Feed =  EarlParser.parseOrThrow(inputStream, 0)

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