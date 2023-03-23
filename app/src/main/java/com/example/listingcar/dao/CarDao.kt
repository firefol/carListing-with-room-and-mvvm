package com.example.listingcar.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {

    @Query("SELECT * FROM car")
    suspend fun getCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Query("SELECT * FROM car ORDER BY mark")
    suspend fun sortCars(): List<Car>

    @Query("SELECT* FROM car WHERE color = :color")
    suspend fun filterCars(color:String): List<Car>
}