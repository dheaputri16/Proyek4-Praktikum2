package com.example.hanyarunrun.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey val id: Int = 1, // Hanya satu profil yang disimpan
    val studentName: String,
    val studentId: String,
    val studentEmail: String,
    val profileImageUri: String? // Disimpan sebagai String (URI lokal atau URL)
)