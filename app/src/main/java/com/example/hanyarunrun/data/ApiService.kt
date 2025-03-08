package com.example.hanyarunrun.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("od_20735_prevalensi_balita_stunting_kabupatenkota_v1")
    suspend fun getStuntingData(
        @Query("limit") limit: Int = 100
    ): ApiResponse
}
