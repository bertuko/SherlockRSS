package com.albertbaron.sherlockrss.models

import java.util.*

data class ArticleList (
        val Title: String = "",
        val Author: String = "",
        val Description: String = "",
        val Link: String = "",
        val ImageLink: String = "",
        var Articles: ArrayList<Article>
)