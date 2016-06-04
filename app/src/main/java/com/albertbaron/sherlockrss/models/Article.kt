package com.albertbaron.sherlockrss.models

import android.os.Parcel
import android.os.Parcelable
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(database = MyDatabase::class)
class Article : BaseModel {

    constructor()

    constructor(cTitle: String,
                cAuthor: String,
                cDescription: String,
                cLink: String,
                cImageLink: String,
                cpublicationDate: Date?) {
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