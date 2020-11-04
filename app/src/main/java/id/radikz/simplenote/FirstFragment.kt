/*
 * SimpleNote
 * FirstFragment.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

/*
 * SimpleNote
 * FirstFragment.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.radikz.simplenote.adapter.NotesAdapter
import id.radikz.simplenote.db.DatabaseHelper
import id.radikz.simplenote.model.Note

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var notes: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Simple Note"

        databaseHelper = DatabaseHelper(requireContext())
        notes = view.findViewById(R.id.list_notes)
        notes.layoutManager = LinearLayoutManager(requireContext())
        notesAdapter = NotesAdapter(requireContext(), mutableListOf())
        notes.adapter = notesAdapter

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_createFragment)
        }

        readDb()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                alertDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun readDb(){
        val data = databaseHelper.read()
        val listNotes = ArrayList<Note>()

        if (data.moveToFirst()) {

            do {
                val id = data.getInt(data.getColumnIndex("_id"))
                val title = data.getString(data.getColumnIndex("title"))
                val description = data.getString(data.getColumnIndex("description"))
                val date = data.getString(data.getColumnIndex("date"))

                listNotes.add(Note(id, title, description, date))

            } while (data.moveToNext())

        }
        notesFetched(listNotes)
    }

    private fun notesFetched(notes: List<Note>) {
        notesAdapter.appendNotes(notes)
    }

    private fun alertDialog(){
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setMessage("Are you sure do you want to delete all item?")
        alertDialogBuilder.setPositiveButton("Yes"){ _,_ ->
            val result: Boolean = databaseHelper.deleteAll()
            if (result){
                deleteAllNote()
            }
            else{
                Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
            }
        }
        alertDialogBuilder.setNeutralButton("Cancel"){ _,_ -> }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun deleteAllNote(){
        notesAdapter.deleteAllNotes()
    }

}