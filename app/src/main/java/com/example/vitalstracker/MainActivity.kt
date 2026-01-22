package com.example.vitalstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.vitalstracker.ui.theme.VitalsTrackerTheme
import com.example.vitalstracker.ui.theme.domain.Notification
import com.example.vitalstracker.ui.theme.domain.VitalViewModel
import com.example.vitalstracker.ui.theme.presentation.HomeScreen
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        scheduleReminder()
        setContent {
            VitalsTrackerTheme {
                val viewModel = remember {
                    VitalViewModel(applicationContext)
                }
                HomeScreen(viewModel = viewModel)
            }
        }
    }

    private fun scheduleReminder() {
        val work = PeriodicWorkRequestBuilder<Notification>(
            5, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "vitals_reminder",
                ExistingPeriodicWorkPolicy.UPDATE,
                work
            )
    }

}

