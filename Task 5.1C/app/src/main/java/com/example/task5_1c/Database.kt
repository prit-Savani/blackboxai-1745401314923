package com.example.task5_1c

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "itube.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_PLAYLIST = "playlist"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_VIDEO_URL = "video_url"

        private const val SQL_CREATE_TABLE_PLAYLIST =
            "CREATE TABLE $TABLE_PLAYLIST (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TITLE TEXT," +
                    "$COLUMN_VIDEO_URL TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_PLAYLIST)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLAYLIST")
        onCreate(db)
    }
}
