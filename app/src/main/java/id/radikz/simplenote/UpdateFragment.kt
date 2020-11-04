/*
 * SimpleNote
 * UpdateFragment.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import id.radikz.simplenote.db.DatabaseHelper
import id.radikz.simplenote.model.Note


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 *
 */

class UpdateFragment : Fragment() {

    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var update: Button
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var note: Note
    private val args: UpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar!!.setTitle("Update")

        title = view.findViewById(R.id.create_title)
        description = view.findViewById(R.id.create_description)
        update = view.findViewById(R.id.create_button)
        databaseHelper = DatabaseHelper(requireContext())

        note = args.note

        update.setText(getString(R.string.update))
        loadData()
        update.setOnClickListener{
            updateData(it)
        }
    }

    private fun loadData(){
        title.setText(note.title)
        description.setText(note.description)
    }

    private fun updateData(view: View){
        val strTitle = title.text.toString()
        val strDesc = description.text.toString()
        val strId: String = note.id.toString()
        val result = databaseHelper.update(strId, strTitle, strDesc)

        if (result) {
            Toast.makeText(requireContext(), "Data updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_updateFragment_to_FirstFragment)
        }
        else{
            Toast.makeText(requireContext(), "Failed to insert data", Toast.LENGTH_SHORT).show()
        }
    }
}