/*
 * SimpleNote
 * CreateFragment.kt
 * Created by Rangga Dikarinata on 2020/11/3
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
import id.radikz.simplenote.db.DatabaseHelper

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CreateFragment : Fragment() {

    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var add: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar!!.setTitle("Create")

        title = view.findViewById(R.id.create_title)
        description = view.findViewById(R.id.create_description)
        add = view.findViewById(R.id.create_button)
        databaseHelper = DatabaseHelper(requireContext())

        add.setOnClickListener{
            insertData(it)
        }
    }

    private fun insertData(view: View){
        val strTitle = title.text.toString()
        val strDesc = description.text.toString()

        val result = databaseHelper.insert(strTitle, strDesc)

        if (result) {
            Navigation.findNavController(view).navigate(R.id.action_createFragment_to_FirstFragment)
        }
        else{
            Toast.makeText(requireContext(), "Failed to insert data", Toast.LENGTH_SHORT).show()
        }
    }
}