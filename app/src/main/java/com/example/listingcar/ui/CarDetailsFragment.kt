package com.example.listingcar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.listingcar.R
import com.example.listingcar.dao.Car
import com.example.listingcar.dao.CarDatabase
import com.example.listingcar.databinding.FragmentBaseBinding
import com.example.listingcar.databinding.FragmentCarDetailsBinding

class CarDetailsFragment : Fragment() {

    private val viewModel: CarDetailsViewModel by activityViewModels()
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var binding: FragmentCarDetailsBinding
    private val carDatabase by lazy { CarDatabase.getDatabase(requireContext()).CarDao() }
    private lateinit var car: Car

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.carsLiveData.observe(activity as LifecycleOwner) {
            Glide.with(binding.imageView.context)
                .load( it.image)
                .into(binding.imageView)
            binding.markDetailsTextView.text = it.mark
            binding.editTextTypeCar.setText(it.type_car)
            binding.editTextHorsePower.setText(it.horsePower.toString())
            binding.editTextColor.setText(it.color)

        }
        binding.button.setOnClickListener {
            val typeCar = binding.editTextTypeCar.text.toString()
            val color = binding.editTextColor.text.toString()
            val horsePower = binding.editTextHorsePower.text.toString()
            val mark = binding.markDetailsTextView.text.toString()
            val image = viewModel.carsLiveData.value?.image.toString()
            car = Car(mark,typeCar,horsePower.toInt(),color,image)
            baseViewModel.updateItem(carDatabase, car)
            findNavController().navigate(R.id.baseFragment)

        }

    }

}