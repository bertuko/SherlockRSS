package com.albertbaron.sherlockrss.models

import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = MyDatabase::class)
 class FeedInfo : BaseModel {

    constructor(){}

    constructor(cname: String, cfeedURL: String) {
        this.name = cname
        this.feedUrl = cfeedURL
    }

    @PrimaryKey(autoincrement = true)
    @Column(name = "id")
    var id: Long = 0  // package-private recommended, not required

    //@Unique
    @Column(name = "name")
    var name: String = ""

    @Column(name = "feedUrl")
    var feedUrl: String = ""

}


