/*
 * SimpleNote
 * NotesAdapter.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

/*
 * SimpleNote
 * NotesAdapter.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import id.radikz.simplenote.FirstFragmentDirections
import id.radikz.simplenote.R
import id.radikz.simplenote.db.DatabaseHelper
import id.radikz.simplenote.model.Note

class NotesAdapter(
    private val context: Context,
    private var notes: MutableList<Note>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    fun appendNotes(notes: List<Note>) {
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun deleteAllNotes(){
        notes.clear()
        notifyDataSetChanged()
    }

    fun deleteNotes(id: Int){
        notes.removeAt(id)
        notifyItemRemoved(id)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun expand(activate: Boolean, textView: TextView){
        if (activate) {
            textView.setMaxLines(3)
            textView.setEllipsize(TextUtils.TruncateAt.END)
        }
        else{
            textView.setSingleLine(false)
            textView.setEllipsize(null)
        }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val releaseDate: TextView = itemView.findViewById(R.id.list_date)
        private val title: TextView = itemView.findViewById(R.id.list_title)
        private val description: TextView = itemView.findViewById(R.id.list_description)
        private val icUpdate: ImageView = itemView.findViewById(R.id.list_ic_update)
        private val icDelete: ImageView = itemView.findViewById(R.id.list_ic_delete_red)
        private var expandable: Boolean = false
        private lateinit var databaseHelper: DatabaseHelper

        fun bind(note: Note) {
            title.setText(note.title)
            releaseDate.setText(note.date)
            description.setText(note.description)

            databaseHelper = DatabaseHelper(context)

            icUpdate.setOnClickListener{
                val direction = FirstFragmentDirections.actionFirstFragmentToUpdateFragment(note)
                itemView.findNavController().navigate(direction)
            }

            icDelete.setOnClickListener{
                Log.d("adapter", note.date)
                val alertDialogBuilder = AlertDialog.Builder(context)
                alertDialogBuilder.setMessage("Are you sure do you want to delete this?")
                alertDialogBuilder.setPositiveButton("Yes"){ _,_ ->

                    val result: Boolean = databaseHelper.delete(note.id.toString())
                    if (result){
                        deleteNotes(getAdapterPosition())
                    }
                    else{
                        Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
                    }
                }
                alertDialogBuilder.setNeutralButton("Cancel"){ _,_ -> }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

            itemView.setOnClickListener {
                Log.d("adapter", note.title)
                TransitionManager.beginDelayedTransition(
                    itemView.getRootView() as ViewGroup,
                    AutoTransition()
                )
                expand(expandable, description)
                expandable = !expandable
            }
        }
    }
}