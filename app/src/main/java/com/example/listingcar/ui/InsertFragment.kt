package com.example.listingcar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.listingcar.R
import com.example.listingcar.dao.Car
import com.example.listingcar.dao.CarDatabase
import com.example.listingcar.databinding.FragmentBaseBinding
import com.example.listingcar.databinding.FragmentInsertBinding


class InsertFragment : Fragment() {

    private lateinit var viewModel: BaseViewModel
    private lateinit var binding: FragmentInsertBinding
    private val carDatabase by lazy { CarDatabase.getDatabase(requireContext()).CarDao() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]

        binding.insertInDBbutton.setOnClickListener {
            val car = Car(
                binding.meditTextTextPersonName.text.toString(),
                binding.typeEditTextTextPersonName.text.toString(),
                binding.hpeditTextNumber.text.toString().toInt(),
                binding.ColorineditTextTextPersonName.text.toString(),
                binding.ImageEditTextTextPersonName.text.toString())
            viewModel.insertInDB(carDatabase,car)
            findNavController().navigate(R.id.baseFragment)
        }
    }

}