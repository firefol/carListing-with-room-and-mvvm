package com.example.listingcar.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listingcar.dao.Car
import com.example.listingcar.dao.CarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel : ViewModel() {

    var liveDataList: MutableLiveData<List<Car>?> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<Car>?> {
        return liveDataList
    }

    fun getCars(carDatabase:CarDao) {
        viewModelScope.launch(Dispatchers.IO) {
            liveDataList.postValue(carDatabase.getCars())
        }
    }

    fun insertInDB(carDatabase:CarDao,cars:Car){
        viewModelScope.launch(Dispatchers.IO) {
            carDatabase.addCar(cars)
            liveDataList.postValue(carDatabase.getCars())
        }
    }
    fun firstInsert(carDatabase:CarDao,cars:List<Car>) {
        viewModelScope.launch(Dispatchers.IO) {
            if (carDatabase.getCars().isEmpty()){
                for (i in cars.indices){
                    carDatabase.addCar(cars[i])
                }
            }
        }
    }

    fun updateItem(carDatabase: CarDao, car: Car){
        viewModelScope.launch(Dispatchers.IO) {
            carDatabase.updateCar(car)
            liveDataList.postValue(carDatabase.getCars())
        }
    }
    fun sortCars(carDatabase: CarDao) {
        viewModelScope.launch(Dispatchers.IO) {
            liveDataList.postValue(carDatabase.sortCars())
        }
    }

    fun filterCars(carDatabase: CarDao,color: String) {
        viewModelScope.launch(Dispatchers.IO) {
            liveDataList.postValue(carDatabase.filterCars(color))
        }
    }

}