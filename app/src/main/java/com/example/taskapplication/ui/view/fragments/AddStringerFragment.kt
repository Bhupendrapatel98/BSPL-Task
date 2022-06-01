package com.example.taskapplication.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.taskapplication.R
import com.example.taskapplication.databinding.FragmentAddStringerBinding
import com.example.taskapplication.ui.viewModel.StringerViewModel
import com.example.taskapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStringerFragment : Fragment() {

    private lateinit var binding: FragmentAddStringerBinding
    private val stringerViewModel: StringerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddStringerBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = stringerViewModel

        stringerViewModel.addLiveData.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                   // Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failed -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    findNavController().popBackStack()
                    Toast.makeText(context, "Add Data SuccessFully", Toast.LENGTH_SHORT).show()
                }
            }
        })


        return binding.root
    }

}