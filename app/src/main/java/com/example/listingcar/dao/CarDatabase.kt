package com.example.listingcar.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Car::class],
    version = 1,
    exportSchema = true
)
abstract class CarDatabase : RoomDatabase() {

    abstract fun CarDao(): CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CarDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CarDatabase::class.java,
                "car_database"
            )
                .build()
        }
    }
}