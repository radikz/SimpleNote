/*
 * SimpleNote
 * DatabaseContainer.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

/*
 * SimpleNote
 * DatabaseContainer.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote.db

import android.provider.BaseColumns

object DatabaseContainer {
    class NoteTable: BaseColumns{
        companion object{
            val TABLE_NAME = "note"
            val TITLE_COLUMN = "title"
            val DESCRIPTION_COLUMN = "description"
            val DATE_COLUMN = "date"
        }
    }
}