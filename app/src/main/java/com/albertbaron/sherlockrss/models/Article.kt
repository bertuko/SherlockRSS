package com.albertbaron.sherlockrss.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Article (
        val Title: String = "",
        val Author: String = "",
        val Description: String = "",
        val Link: String = "",
        val ImageLink: String = "",
        val publicationDate: Date? = null
) : Parcelable {

    constructor(source: Parcel): this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            if (source.readLong().compareTo(0) == 0) null else Date(source.readLong())
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.Title)
        dest?.writeString(this.Author)
        dest?.writeString(this.Description)
        dest?.writeString(this.Link)
        dest?.writeString(this.ImageLink)
        dest?.writeLong(publicationDate?.time ?: 0)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article{
                return Article(source)
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }
        }
    }
}