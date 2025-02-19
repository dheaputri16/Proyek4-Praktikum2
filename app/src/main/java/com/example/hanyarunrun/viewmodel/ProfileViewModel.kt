package com.example.hanyarunrun.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.hanyarunrun.data.ProfileEntity
import com.example.hanyarunrun.data.repository.ProfileRepository

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    val profile = repository.getProfile()

    fun updateProfile(studentName: String, studentId: String, studentEmail: String, profileImageUri: Uri?) {
        viewModelScope.launch {
            repository.updateProfile(
                studentName = studentName,
                studentId = studentId,
                studentEmail = studentEmail,
                profileImageUri = profileImageUri?.toString() // Convert Uri ke String
            )
        }
    }
}