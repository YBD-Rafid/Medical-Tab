package com.example.medical_tab.api
import com.example.medical_tab.model.RequestResponseModel
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.model.TokenRaiseRequest
import com.example.medical_tab.model.TokenRaiseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/Support/GetLines")
    suspend fun getSectionLines(): List<SectionLineModel>

    @POST("api/Support/TokenRaise")
    suspend fun submitSelection(@Body request: TokenRaiseRequest): TokenRaiseResponse

    @GET("api/Support/GetDailyMedicalRequests?date=2026-08-13")
    suspend fun getPrescriptions(): RequestResponseModel
}
