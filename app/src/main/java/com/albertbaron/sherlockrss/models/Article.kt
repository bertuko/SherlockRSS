package com.albertbaron.sherlockrss.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import org.parceler.Parcel
import java.util.*

@Table(database = MyDatabase::class)
@Parcel(value = Parcel.Serialization.BEAN, analyze = arrayOf(Article::class) )
class Article : BaseModel {

    constructor()

    constructor(cFeedInfoId: Long,
                cTitle: String,
                cAuthor: String,
                cDescription: String,
                cLink: String,
                cImageLink: String,
                cpublicationDate: Date?
    ) {
        this.FeedInfoId = cFeedInfoId
        this.Title = cTitle
        this.Author = cAuthor
        this.Description = cDescription
        this.Link = cLink
        this.ImageLink = cImageLink
        this.publicationDate = cpublicationDate
    }

    @PrimaryKey(autoincrement = true)
    @Column
    var id: Long = 0

    @Column
    var FeedInfoId: Long = 0

    @Column
    var Title: String = ""

    @Column
    var Author: String = ""

    @Column
    var Description: String = ""

    @Column
    var Link: String = ""

    @Column
    var ImageLink: String = ""

    @Column
    var publicationDate: Date? = null

}