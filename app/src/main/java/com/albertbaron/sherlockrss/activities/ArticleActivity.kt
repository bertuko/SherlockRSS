package com.albertbaron.sherlockrss.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.albertbaron.sherlockrss.helpers.HtmlCodeHelper
import com.albertbaron.sherlockrss.layouts.ArticleActivityUI
import com.albertbaron.sherlockrss.models.Article
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.parceler.Parcels
import java.util.*
import android.view.MenuItem

class ArticleActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var mViewPager: ViewPager? = null
    private var artList: MutableList<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ui = ArticleActivityUI()
        ui.setContentView(this)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val position: Int = intent.getIntExtra("Position", 0)
        artList = Parcels.unwrap<MutableList<Article>>(intent.getParcelableExtra<Parcelable>("Article"))

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        mViewPager = ui.holder!!.aContainer
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.currentItem = position
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val pos: ArrayList<Int> = arguments.getIntegerArrayList(ARG_SECTION_NUMBER)
            val parentAct = activity as ArticleActivity
            val art: Article = parentAct.artList!![pos[0]]

            return UI {
                verticalLayout {
                    val tv = textView() {
                        textSize = 16f
                    }.lparams(width = matchParent){
                        horizontalMargin = dip(8)
                        verticalMargin = dip(3)
                    }
                    tv.setTextColor(Color.BLACK)
                    tv.text = art.Title
                    val wv = webView() {
                        settings.defaultTextEncodingName = "utf-8"
                        settings.javaScriptEnabled = true
                        settings.cacheMode = WebSettings.LOAD_NO_CACHE
                    }.lparams(width = matchParent)
                    val code: String = HtmlCodeHelper.Companion.AdaptCodeToWebView(art.Description)
                    wv.loadData(code, "text/html; charset=utf-8", "utf-8")
                }
            }.view

        }

        companion object {

            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putIntegerArrayList(ARG_SECTION_NUMBER, arrayListOf(sectionNumber))
                fragment.arguments = args
                return fragment
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return artList!!.count()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return artList!![position].Title
        }
    }
}
