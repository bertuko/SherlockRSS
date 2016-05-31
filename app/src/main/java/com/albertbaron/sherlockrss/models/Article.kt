package com.albertbaron.sherlockrss.models

import java.util.*

data class Article (
        val Title: String = "",
        val Author: String = "",
        val Description: String = "",
        val Link: String = "",
        val ImageLink: String = "",
        val publicationDate: Date? = null
)