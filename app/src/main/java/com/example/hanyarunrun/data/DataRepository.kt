package com.example.hanyarunrun.data

import android.app.Application
import com.example.hanyarunrun.data.AppDatabase
import com.example.hanyarunrun.data.DataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(application: Application) {
    private val dao = AppDatabase.getDatabase(application).dataDao()

    suspend fun fetchAndSaveData() {
        try {
            val response = ApiClient.instance.getStuntingData()
            if (response.error == 0) {
                val entities = response.data.map { apiData ->
                    val apiResponse = DataEntity(
                        kodeProvinsi = apiData.kode_provinsi,
                        namaProvinsi = apiData.nama_provinsi,
                        kodeKabupatenKota = apiData.kode_kabupaten_kota,
                        namaKabupatenKota = apiData.nama_kabupaten_kota,
                        total = apiData.prevalensi_balita_stunting,
                        satuan = apiData.satuan,
                        tahun = apiData.tahun
                    )
                }
                withContext(Dispatchers.IO) {
                    dao.insertAll(entities)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
