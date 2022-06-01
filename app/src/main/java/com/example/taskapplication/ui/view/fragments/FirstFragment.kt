package com.example.taskapplication.ui.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.taskapplication.R

class FirstFragment : Fragment() {

    private lateinit var view1: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show() //hide Toolbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false) //hide backButton
        setHasOptionsMenu(true);
        view1 = inflater.inflate(R.layout.fragment_first, container, false)

        return view1
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.stringers_details) {
            Navigation.findNavController(view1)
                .navigate(R.id.action_firstFragment_to_stringerDetailsFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}