package com.example.vitalstracker.ui.theme.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    suspend fun insert(vitalEntity: VitalEntity)

    @Query("SELECT * FROM vitals ORDER BY time DESC")
    fun getAllVitals(): Flow<List<VitalEntity>>
}