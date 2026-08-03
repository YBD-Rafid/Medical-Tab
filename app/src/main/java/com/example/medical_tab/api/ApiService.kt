package com.example.medical_tab.api

import com.example.medical_tab.model.SectionLineModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api/Support/GetLines")
    suspend fun getSectionLines(): List<SectionLineModel>

    @POST("api/Support/TokenRaise") // Replace with actual endpoint when available
    suspend fun submitSelection(
        @Query("IdCardNo") idCard: String,
        @Query("LineId") lineId: String): Boolean
}
