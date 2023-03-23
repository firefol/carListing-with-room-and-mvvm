package com.example.listingcar.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listingcar.R
import com.example.listingcar.dao.Car
import com.example.listingcar.dao.CarDatabase
import com.example.listingcar.databinding.FragmentBaseBinding
import com.example.listingcar.util.DataCars

class BaseFragment : Fragment(){

    private lateinit var viewModel: BaseViewModel
    private val carDetailsviewModel: CarDetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentBaseBinding
    private lateinit var recyclerAdapter: CarsAdapter
    private val carDatabase by lazy { CarDatabase.getDatabase(requireContext()).CarDao() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        initRecycleView()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        viewModel.getCars(carDatabase)
                    }
                    1 -> {
                        viewModel.sortCars(carDatabase)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.filterButton.setOnClickListener {
            viewModel.filterCars(carDatabase, "синий")
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.insertFragment)
        }
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        val cars = DataCars().getCars()
        viewModel.firstInsert(carDatabase,cars)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setNewsList(it)
                recyclerAdapter.notifyDataSetChanged()
            }
        }
        viewModel.getCars(carDatabase)

    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        recyclerAdapter = CarsAdapter()
        val ItemTouchHelper = ItemTouchHelper(simpleCallback)
        ItemTouchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.adapter = recyclerAdapter
    }
    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            TODO("Not yet implemented")
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT ->{
                    val car = viewHolder.itemView.tag as Car
                    carDetailsviewModel.carsLiveData.value = car
                    findNavController().navigate(R.id.carDetailsFragment)


                }
            }
        }
    }
}