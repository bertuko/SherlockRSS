package com.albertbaron.sherlockrss.layouts

import android.webkit.WebSettings
import android.webkit.WebView
import com.albertbaron.sherlockrss.activities.ArticleActivity
import org.jetbrains.anko.*

class ArticleActivityUI : AnkoComponent<ArticleActivity> {
    var holder: ArticleListHolder? = null

    override fun createView(ui: AnkoContext<ArticleActivity>) = with(ui) {
        verticalLayout {
            val wv = webView() {
                settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            }.lparams(width = matchParent)
            holder = ArticleListHolder(
                    aWebView = wv
            )
        }
    }

    data class ArticleListHolder (
            var aWebView: WebView
    )

}