package com.albertbaron.sherlockrss.models

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION, generatedClassSeparator = "$")
object MyDatabase {
    const val NAME: String = "MyDatabase"
    const val VERSION: Int = 1
}