/*
 * SimpleNote
 * DatabaseHelper.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 * Copyright © 2020 Rangga Dikarinata. All rights reserved.
 */

package id.radikz.simplenote.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import id.radikz.simplenote.db.DatabaseContainer.NoteTable.Companion.DATE_COLUMN
import id.radikz.simplenote.db.DatabaseContainer.NoteTable.Companion.DESCRIPTION_COLUMN
import id.radikz.simplenote.db.DatabaseContainer.NoteTable.Companion.TABLE_NAME
import id.radikz.simplenote.db.DatabaseContainer.NoteTable.Companion.TITLE_COLUMN
import java.text.SimpleDateFormat
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "note.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val noteTable = "create table " +
                TABLE_NAME + " (" +
                BaseColumns._ID + " integer primary key autoincrement, " +
                TITLE_COLUMN + " text, " +
                DESCRIPTION_COLUMN + " text, " +
                DATE_COLUMN + " text" + ") "
        db!!.execSQL(noteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $TABLE_NAME")
    }

    fun insert(title: String, desc: String): Boolean{
        val db: SQLiteDatabase = this.writableDatabase
        val content = ContentValues()
        content.put(TITLE_COLUMN, title)
        content.put(DESCRIPTION_COLUMN, desc)
        content.put(DATE_COLUMN, getDateTime())
        val insertData = db.insert(TABLE_NAME,null, content)
        db.close()

        return !insertData.equals(-1)
    }

    fun read(): Cursor{
        val db: SQLiteDatabase = this.readableDatabase
        val readDb: Cursor = db.rawQuery("select * from $TABLE_NAME", null)
        return readDb
    }

    fun delete(): Boolean{
        val db: SQLiteDatabase = this.writableDatabase
        val data = db.delete(TABLE_NAME, null, null)
        return !data.equals(-1)
    }

    private fun getDateTime(): String? {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }

}

