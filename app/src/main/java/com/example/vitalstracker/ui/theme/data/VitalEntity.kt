package com.example.vitalstracker.ui.theme.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "vitals")
data class VitalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val bpSys: String,
    val bpDia: String,
    val heartRate: String,
    val weight: String,
    val kicks : String,
    val time: Long = System.currentTimeMillis()

)
