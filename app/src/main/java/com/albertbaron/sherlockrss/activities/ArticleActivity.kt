package com.albertbaron.sherlockrss.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.albertbaron.sherlockrss.layouts.ArticleActivityUI
import com.albertbaron.sherlockrss.models.Article
import org.jetbrains.anko.setContentView

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ui = ArticleActivityUI()
        ui.setContentView(this)
        val art: Article = Article(
               cFeedInfoId =  0,
               cTitle =  intent.getStringExtra("Title"),
               cAuthor =  intent.getStringExtra("Author"),
               cDescription =  intent.getStringExtra("Description"),
               cLink =  intent.getStringExtra("Link"),
               cImageLink =  intent.getStringExtra("ImageLink"),
               cpublicationDate =  null
        )
        ui.holder!!.aTitle.text = art.Title
        val head: String = "<head><style>img,iframe{max-width: 100%; width:auto; height: auto;}</style></head>";
        val code: String = "<html>" + head + "<body>" + art.Description + "</body></html>"
        ui.holder!!.aWebView.loadData(code, "text/html; charset=utf-8", "utf-8")
    }

}
