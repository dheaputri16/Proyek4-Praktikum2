package com.example.hanyarunrun.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kode_provinsi: String,
    val nama_provinsi: String,
    val kode_kabupaten_kota: String,
    val nama_kabupaten_kota: String,
    val prevalensi_balita_stunting: Double,
    val satuan: String,
    val tahun: Int
)

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = false) // Hanya ada satu profil pengguna
    val id: Int = 1, // Default ID untuk profil
    val studentName: String,
    val studentId: String,
    val studentEmail: String
)

