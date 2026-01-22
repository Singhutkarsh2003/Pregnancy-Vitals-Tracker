package com.example.vitalstracker.ui.theme.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [VitalEntity::class], version = 1)
 abstract  class AppDatabase: RoomDatabase() {

    abstract  fun vitalDao(): Dao

    companion object{
        fun getDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "vital_db"
            ).build()
        }
    }
}