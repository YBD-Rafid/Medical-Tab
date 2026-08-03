package com.example.medical_tab.repository

import android.util.Log
import com.example.medical_tab.api.ApiService
import com.example.medical_tab.model.SectionLineModel

class MedicalRepository(private val apiService: ApiService) {
    
    suspend fun getSectionLines(): Result<List<SectionLineModel>> {
        return try {
            Log.d("MedicalRepo", "Calling API: getSectionLines")
            val response = apiService.getSectionLines()
            Log.d("MedicalRepo", "Response Success: Found ${response.size} items")
            Log.d("MedicalRepo", "Data: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("MedicalRepo", "API Error fetching lines: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun submitMedicalInfo(idCard: String, lineId: String): Result<Boolean> {
        return try {
            Log.d("MedicalRepo", "Submitting Info - ID: $idCard, Line: $lineId")
            val success = apiService.submitSelection(idCard, lineId)
            Log.d("MedicalRepo", "Submission Result: $success")
            Result.success(success)
        } catch (e: Exception) {
            Log.e("MedicalRepo", "Submission Error: ${e.message}", e)
            Result.failure(e)
        }
    }
}
