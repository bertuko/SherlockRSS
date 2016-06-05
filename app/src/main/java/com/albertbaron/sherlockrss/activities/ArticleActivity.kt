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
import com.albertbaron.sherlockrss.R
import com.albertbaron.sherlockrss.layouts.ArticleActivityUI
import com.albertbaron.sherlockrss.models.Article
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.parceler.Parcels

class ArticleActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null
    private var artList: MutableList<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ui = ArticleActivityUI()
        ui.setContentView(this)
        //setContentView(R.layout.activity_tab_article)

        val position: Int = intent.getIntExtra("Position", 0)
        artList = Parcels.unwrap<MutableList<Article>>(intent.getParcelableExtra<Parcelable>("Article"))

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = ui.holder!!.aContainer
        //mViewPager = findViewById(R.id.container) as ViewPager?
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.currentItem = position
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val pos: Int = arguments.getInt(ARG_SECTION_NUMBER)
            val parentAct = activity as ArticleActivity
            val art: Article = parentAct.artList!![pos]

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
                    }.lparams(width = matchParent)
                    val head: String = "<head><style>img,iframe{max-width: 100%; width:auto; height: auto;}</style></head>"
                    val code: String = "<html>" + head + "<body>" + art.Description + "</body></html>"
                    wv.loadData(code, "text/html; charset=utf-8", "utf-8")
                }
            }.view

        }

        companion object {

            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
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
