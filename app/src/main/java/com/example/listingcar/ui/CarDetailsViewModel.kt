package com.example.listingcar.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listingcar.dao.Car


class CarDetailsViewModel:ViewModel() {
    val carsLiveData: MutableLiveData<Car> by lazy {
        MutableLiveData<Car>()
    }
}