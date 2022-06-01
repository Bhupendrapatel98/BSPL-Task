package com.example.taskapplication.ui.view.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.taskapplication.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide() //hide Toolbar
        val view: View = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_firstFragment)
        }, 2000)

        return view
    }

}