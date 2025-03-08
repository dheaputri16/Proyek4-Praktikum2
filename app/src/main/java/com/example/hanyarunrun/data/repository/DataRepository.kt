package com.example.hanyarunrun.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader

class DataRepository {
    private val client = OkHttpClient()

    suspend fun fetchData(): List<String> {
        return withContext(Dispatchers.IO) {
            val url = "https://data.jabarprov.go.id/api-backend/bigdata/dinkes/od_15940_jumlah_kasus_penyakit_berdasarkan_jenis_penyakit?download=csv"

            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) throw Exception("Failed to fetch data: ${response.code}")

            response.body?.let { responseBody ->
                BufferedReader(InputStreamReader(responseBody.byteStream())).readLines()
            } ?: emptyList()
        }
    }
}
