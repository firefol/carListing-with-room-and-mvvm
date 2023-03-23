package com.example.listingcar.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    @ColumnInfo(name = "mark") val mark: String,
    @ColumnInfo(name = "type_car") val type_car: String?,
    @ColumnInfo(name = "horse_power") val horsePower: Int?,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "image") val image: String?,
)
