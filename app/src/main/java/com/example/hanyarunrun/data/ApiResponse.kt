package com.example.hanyarunrun.data

data class ApiResponse(
    val message: String,
    val error: Int,
    val data: List<ApiData>
)

data class ApiData(
    val id: Int,
    val kode_provinsi: String,
    val nama_provinsi: String,
    val kode_kabupaten_kota: String,
    val nama_kabupaten_kota: String,
    val prevalensi_balita_stunting: Double,
    val satuan: String,
    val tahun: Int
)
