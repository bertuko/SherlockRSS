package com.albertbaron.sherlockrss.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.layouts.ArticleActivityUI
import com.albertbaron.sherlockrss.models.Article
import org.jetbrains.anko.setContentView

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ui = ArticleActivityUI()
        ui.setContentView(this)
        //setContentView(R.layout.activity_article)
        val art: Article = Article(
                intent.getStringExtra("Title"),
                intent.getStringExtra("Author"),
                intent.getStringExtra("Description"),
                intent.getStringExtra("Link"),
                intent.getStringExtra("ImageLink"),
                null
        )
        val wv: WebView = ui.holder!!.aWebView
        //val wv: WebView = findViewById(R.id.webView) as WebView
        //wv.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        val head: String = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        val code :String= "<html>" + head + "<body>" + art.Description + "</body></html>"
        wv.loadData(code, "text/html", null)
    }

}
