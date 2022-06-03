package com.example.taskapplication.ui.view.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapplication.R
import com.example.taskapplication.data.model.StringerListItem
import com.example.taskapplication.databinding.FragmentStringerDetailsBinding
import com.example.taskapplication.databinding.UpdateStringerBinding
import com.example.taskapplication.ui.view.adapter.StringerAdapter
import com.example.taskapplication.ui.view.adapter.StringerAdapter.OnItemClickListener
import com.example.taskapplication.ui.viewModel.StringerViewModel
import com.example.taskapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.recreate

@AndroidEntryPoint
class StringerDetailsFragment : Fragment() {

    private lateinit var view1: View
    private val stringerViewModel: StringerViewModel by viewModels()
    private lateinit var binding: FragmentStringerDetailsBinding
    private lateinit var stringerAdapter: StringerAdapter
    private lateinit var stringerListItem: MutableList<StringerListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStringerDetailsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.stringerAddButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_stringerDetailsFragment_to_addStringerFragment)
        }

        stringerViewModel.getData()
        stringerViewModel.postLiveData.observe(this, Observer {

            when (it) {

                is Resource.Loading -> {
                    // Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failed -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.list = it.data

                    stringerListItem = mutableListOf()
                    stringerListItem = it.data

                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    stringerAdapter = StringerAdapter(it.data, object : OnItemClickListener {
                        override fun onItemClick(stringerId: Int, position: Int, type: String) {
                            if (type == "Delete") {
                                stringerViewModel.deleteStringer(stringerId)
                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                showDialog(it.data, position, stringerId)
                            }
                        }
                    })
                    binding.recyclerView.adapter = stringerAdapter
                    //Toast.makeText(context, it.data.get(0).Address, Toast.LENGTH_SHORT).show()
                }
            }
        })


        return binding.root
    }

    private fun showDialog(
        listItem: MutableList<StringerListItem>,
        position: Int,
        stringerId: Int
    ) {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)

        val binding1: UpdateStringerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.update_stringer, null, false
        )
        dialog?.setContentView(binding1.root)

        stringerViewModel.name.value = listItem[position].Name
        stringerViewModel.age.value = listItem[position].Age.toString()
        stringerViewModel.address.value = listItem[position].Address
        stringerViewModel.phoneNumber.value = listItem[position].PhoneNumber
        stringerViewModel.startTime.value = listItem[position].StartTiming
        stringerViewModel.password.value = listItem[position].Password
        stringerViewModel.closeTime.value = listItem[position].CloseTiming
        stringerViewModel.stringerID.value = stringerId.toString()
        binding1.viewModel = stringerViewModel

        binding1.button.setOnClickListener {

            var s = StringerListItem(
                stringerViewModel.address.value.toString(),
                Integer.parseInt(stringerViewModel.age.value.toString()),
                stringerViewModel.closeTime.value.toString(),
                stringerViewModel.name.value.toString(),
                stringerViewModel.password.value.toString(),
                stringerViewModel.phoneNumber.value.toString(),
                stringerViewModel.startTime.value.toString(),
                Integer.parseInt(stringerViewModel.stringerID.value.toString())
            )

            stringerListItem[position] = s
            stringerAdapter.notifyItemChanged(position) //update

            //stringerListItem.add(stringerListItem.size-1,s)
            //stringerAdapter.notifyItemInserted(stringerListItem.size-1) //addition

            stringerViewModel.upDateData()
            dialog?.dismiss()
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }

        dialog?.show()
        val window: Window = dialog?.window!!
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    }

}