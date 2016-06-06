package com.albertbaron.sherlockrss.layouts

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class ArticleListUI {
    var v: View? = null
    var t1: TextView? = null
    var t2: TextView? = null
    constructor(ctx: Context) {
        v = ctx.UI{
            linearLayout {
                gravity = Gravity.BOTTOM
                isLongClickable = false
                isMeasureWithLargestChildEnabled = false
                //isNestedScrollingEnabled = false
                orientation = LinearLayout.VERTICAL
                val vt1 = textView("Medium Text") {
                    padding = dip(5)
                    setTextIsSelectable(false)
                    textSize = 14f
                }.lparams(
                        width = dip(120),
                        height = dip(95)
                )
                val vt2 = textView("Small Text") {
                    horizontalPadding = dip(5)
                    bottomPadding = dip(5)
                }.lparams(
                        width = dip(120),
                        height = dip(25)
                )
                setHolders(vt1, vt2)
            }
        }.view
    }
    fun setHolders(ht1: TextView, ht2: TextView){
        t1 = ht1
        t2 = ht2
    }
}