package com.example.hanyarunrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.hanyarunrun.data.AppDatabase
import com.example.hanyarunrun.data.repository.ProfileRepository
import com.example.hanyarunrun.ui.AppNavHost
import com.example.hanyarunrun.ui.theme.HanyarunrunTheme
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.viewmodel.DataViewModelFactory
import com.example.hanyarunrun.viewmodel.ProfileViewModel
import com.example.hanyarunrun.viewmodel.ProfileViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanyarunrunTheme {
                // Untuk ProfileViewModel
                val repository = ProfileRepository(AppDatabase.getDatabase(application).profileDao())
                val profileViewModel: ProfileViewModel by viewModels {
                    ProfileViewModelFactory(repository)
                }
                // Inisialisasi ViewModel
                val database = AppDatabase.getDatabase(applicationContext)
                val profileDao = database.profileDao()
                val dataViewModel: DataViewModel by viewModels {
                    DataViewModelFactory(application)
                }
                // Menampilkan Navigation Host
                AppNavHost(viewModel = dataViewModel)
            }
        }
    }
}
