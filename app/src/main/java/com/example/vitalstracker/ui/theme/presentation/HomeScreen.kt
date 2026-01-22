package com.example.vitalstracker.ui.theme.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vitalstracker.R
import com.example.vitalstracker.ui.theme.data.VitalEntity
import com.example.vitalstracker.ui.theme.domain.VitalViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: VitalViewModel){

    val vital: List<VitalEntity> by viewModel.vitalList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Track My Pregnancy") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF9C5FD6),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showDialog = true},
                containerColor = Color(0xFF9C5FD6)
            ) {
              Icon(painter = painterResource(id = R.drawable.baseline_add_24),
                  "Add",
                  tint = Color.White
                  )
            }
        }
    ){paddingValues ->
        LazyColumn (modifier = Modifier.padding(paddingValues)){
            items(vital){
                Card (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ){
                    Column (modifier = Modifier.padding(15.dp)){
                        Text("Bp:${it.bpSys}/${it.bpDia}")
                        Text("Heart Rate:${it.heartRate}")
                        Text("Weight:${it.weight}")
                        Text("Baby Kicks:${it.kicks}")
                    }
                }
            }
        }
    }
    if (showDialog){
        AddVitalsDialog(
            onDismiss ={showDialog = false},
            onSave = {viewModel.insert(it)
            showDialog = false
            }
        )
    }
}