package com.albertbaron.sherlockrss.layouts

import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewPager
import com.albertbaron.sherlockrss.activities.ArticleActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.viewPager

class ArticleActivityUI : AnkoComponent<ArticleActivity> {
    var holder: ArticleListHolder? = null
    override fun createView(ui: AnkoContext<ArticleActivity>) = with(ui) {
        coordinatorLayout{
            fitsSystemWindows = true
            val container = viewPager() {
                id = 12354
            }.lparams(width = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
            holder = ArticleListHolder(
                    aContainer = container
            )
        }
    }

    data class ArticleListHolder (
            var aContainer: ViewPager
    )

}