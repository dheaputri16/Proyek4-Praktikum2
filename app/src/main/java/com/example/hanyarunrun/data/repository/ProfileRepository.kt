package com.example.hanyarunrun.data.repository


import androidx.lifecycle.LiveData
import com.example.hanyarunrun.data.ProfileDao
import com.example.hanyarunrun.data.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val profileDao: ProfileDao) {

    fun getProfile(): LiveData<ProfileEntity?> {
        return profileDao.getProfile()
    }

    suspend fun updateProfile(studentName: String, studentId: String, studentEmail: String) {
        val profile = ProfileEntity(
            id = 1, // ID tetap 1 karena hanya ada satu profil
            studentName = studentName,
            studentId = studentId,
            studentEmail = studentEmail
        )
        profileDao.insertOrUpdate(profile)
    }
}