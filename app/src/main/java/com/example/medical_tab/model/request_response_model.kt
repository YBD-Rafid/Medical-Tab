package com.example.medical_tab.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Model class for Prescription API Response
 */
data class RequestResponseModel(
    @SerializedName("Success")
    val success: Boolean = false,

    @SerializedName("Message")
    val message: String = "",

    @SerializedName("Date")
    val date: String = "",

    @SerializedName("TotalRecords")
    val totalRecords: Int = 0,

    @SerializedName("Data")
    val data: List<Prescription> = emptyList()
) {
    // Helper method to check if response is successful
    fun isSuccessful(): Boolean = success

    // Helper method to get formatted date
    fun getFormattedDate(): String? {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val dateObj = inputFormat.parse(date)
            dateObj?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            date
        }
    }
}

/**
 * Model class for individual Prescription
 */
data class Prescription(
    @SerializedName("PrescriptionId")
    val prescriptionId: Int = 0,

    @SerializedName("IdCardNo")
    val idCardNo: String = "",

    @SerializedName("EmployeeName")
    val employeeName: String = "",

    @SerializedName("DepartmentName")
    val departmentName: String = "",

    @SerializedName("SerialNo")
    val serialNo: String = "",

    @SerializedName("RequestDate")
    val requestDate: String = ""
) {
    // Helper method to get formatted request date
    fun getFormattedRequestDate(): String? {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            val dateObj = inputFormat.parse(requestDate)
            dateObj?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            requestDate
        }
    }

    // Helper method to get only date part
    fun getRequestDateOnly(): String? {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val dateObj = inputFormat.parse(requestDate)
            dateObj?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            requestDate
        }
    }

    // Helper method to get only time part
    fun getRequestTimeOnly(): String? {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.getDefault())
            val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val dateObj = inputFormat.parse(requestDate)
            dateObj?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            requestDate
        }
    }
}