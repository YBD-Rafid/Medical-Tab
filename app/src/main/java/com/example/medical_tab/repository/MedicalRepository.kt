package com.example.medical_tab.repository

import android.util.Log
import com.example.medical_tab.api.ApiService
import com.example.medical_tab.model.Prescription
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.model.TokenRaiseRequest
import com.example.medical_tab.model.UserModel

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

    suspend fun getTodayRequests(): Result<List<Prescription>> {
        return try {
            Log.d("MedicalRepo", "Calling API: getPrescriptions")
            val response = apiService.getPrescriptions()
            if (response.success) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Log.e("MedicalRepo", "API Error fetching prescriptions: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun submitMedicalInfo(idCard: String, lineId: String, urgencyType: Int): Result<Boolean> {
        return try {
            Log.d("MedicalRepo", "Submitting Info - ID: $idCard, Line: $lineId, UrgencyType: $urgencyType")
            val request = TokenRaiseRequest(IdCardNo = idCard, LineId = lineId, UrgencyType = urgencyType)
            val response = apiService.submitSelection(request)
            Log.d("MedicalRepo", "Submission Result: $response")
            val isSuccess = response.output == "success"
            Result.success(isSuccess)
        }
        catch (e: Exception) {
            Log.e("MedicalRepo", "Submission Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun getAllUsers(): Result<List<UserModel>> {
        return try {
            Log.d("MedicalRepo", "Calling API: getAllUsers")
            val response = apiService.getAllUsers()
            Log.d("MedicalRepo", "Response Success: Found ${response.size} items")
            Log.d("MedicalRepo", "Data: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("MedicalRepo", "API Error fetching lines: ${e.message}", e)
            Result.failure(e)
        }
    }
}
