package com.example.vitalstracker.ui.theme.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalstracker.ui.theme.data.AppDatabase
import com.example.vitalstracker.ui.theme.data.VitalEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalViewModel(context: Context): ViewModel() {

    private  val dao = AppDatabase
        .getDatabase(context)
        .vitalDao()

    val vitalList = dao.getAllVitals()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
    fun insert(vitals: VitalEntity){
        viewModelScope.launch {  dao.insert(vitals)}
    }
}