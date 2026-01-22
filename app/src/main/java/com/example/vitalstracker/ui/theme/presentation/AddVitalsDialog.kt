package com.example.vitalstracker.ui.theme.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.room.util.TableInfo
import com.example.vitalstracker.ui.theme.data.VitalEntity

@Composable
fun  AddVitalsDialog(
    onDismiss : () -> Unit,
    onSave: (VitalEntity) -> Unit
){

    var sys by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onSave(
                    VitalEntity(
                        bpSys = sys, bpDia = dia,
                        heartRate = heartRate,
                        weight = weight,
                        kicks = kicks
                    )
                )
            }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Vitals") },
        text = {
            Column {
                OutlinedTextField(sys,{sys =it},
                    label = {Text("Bp Sys")}
                )
                OutlinedTextField(dia,{dia =it},
                    label = {Text("Bp Dia")}
                )
                OutlinedTextField(heartRate,{heartRate =it},
                    label = {Text("Heart Rate")}
                )
                OutlinedTextField(weight,{ weight =it},
                    label = {Text("Weight")}
                )
                OutlinedTextField(kicks,{kicks =it},
                    label = {Text("Baby Kick")}
                )

            }
        }



    )

}