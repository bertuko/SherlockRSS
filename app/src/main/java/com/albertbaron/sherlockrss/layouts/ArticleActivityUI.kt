package com.albertbaron.sherlockrss.layouts

import android.graphics.Color
import android.webkit.WebView
import android.widget.TextView
import com.albertbaron.sherlockrss.activities.ArticleActivity
import org.jetbrains.anko.*

class ArticleActivityUI : AnkoComponent<ArticleActivity> {
    var holder: ArticleListHolder? = null

    override fun createView(ui: AnkoContext<ArticleActivity>) = with(ui) {
        verticalLayout {
            val tv = textView() {
                textSize = 16f
            }.lparams(width = matchParent){
                horizontalMargin = dip(8)
                verticalMargin = dip(3)
            }
            tv.setTextColor(Color.BLACK)
            val wv = webView() {
                settings.defaultTextEncodingName = "utf-8"
                settings.javaScriptEnabled = true
            }.lparams(width = matchParent)

            holder = ArticleListHolder(
                    aWebView = wv,
                    aTitle = tv
            )
        }
    }

    data class ArticleListHolder (
            var aWebView: WebView,
            var aTitle: TextView
    )

}