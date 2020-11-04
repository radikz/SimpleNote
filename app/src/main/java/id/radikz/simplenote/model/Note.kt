/*
 * SimpleNote
 * Note.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

/*
 * SimpleNote
 * Note.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note(var id: Int, var title: String, var description: String, var date: String): Parcelable {

}