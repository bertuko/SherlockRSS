package com.albertbaron.sherlockrss.layouts

import android.support.v7.widget.RecyclerView
import com.albertbaron.sherlockrss.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI : AnkoComponent<MainActivity> {
    var holder: MainActivityHolder? = null

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            val rv = recyclerView()

            holder = MainActivityHolder(
                    feedView = rv
            )
        }
    }

    data class MainActivityHolder (
            var feedView: RecyclerView
    )

}