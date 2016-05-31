package com.albertbaron.sherlockrss.helpers

import android.content.Context
import android.database.sqlite.*
import org.jetbrains.anko.db.*

class DBHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables (more info about that is below)
        /*
        db.execSQL("create table articulos (" +
                "Code int primary key, " +
                "title text," +
                "author text," +
                "publicationDate date," +
                "description text" +
                ")")
        db.createTable("Customer", ifNotExists = true,
                "_id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "name" to TEXT,
                "photo" to BLOB)
        */
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
    }
}

// Access property for Context
val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)