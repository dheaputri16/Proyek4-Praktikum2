package com.example.hanyarunrun.data.repository

import com.example.hanyarunrun.data.ProfileDao
import com.example.hanyarunrun.data.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val profileDao: ProfileDao) {

    fun getProfile(): Flow<ProfileEntity?> {
        return profileDao.getProfile()
    }

    suspend fun updateProfile(studentName: String, studentId: String, studentEmail: String, profileImageUri: String?) {
        val profile = ProfileEntity(
            id = 1, // Tetap pakai ID 1 karena hanya satu user
            studentName = studentName,
            studentId = studentId,
            studentEmail = studentEmail,
            profileImageUri = profileImageUri
        )
        profileDao.insertOrUpdateProfile(profile)
    }
}