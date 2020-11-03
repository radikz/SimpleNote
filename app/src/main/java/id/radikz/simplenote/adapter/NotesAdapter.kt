/*
 * SimpleNote
 * NotesAdapter.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 * Copyright © 2020 Rangga Dikarinata. All rights reserved.
 */

package id.radikz.simplenote.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
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

    fun deleteNotes(){
        notes.clear()
        notifyDataSetChanged()
//        notifyItemRemoved(id)
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
        private var expandable: Boolean = false
        private lateinit var databaseHelper: DatabaseHelper

        fun bind(note: Note) {
            title.setText(note.title)
            releaseDate.setText(note.date)
            description.setText(note.description)

            databaseHelper = DatabaseHelper(context)

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