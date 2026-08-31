package com.example.medical_tab.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("IdCardNo")
    val idCardNo: String,

    @SerializedName("FullName")
    val fullName: String,

    @SerializedName("DesignationName")
    val designationName: String,

    @SerializedName("DepartmentName")
    val departmentName: String,

    @SerializedName("GradeId")
    val gradeId: Int,

    @SerializedName("DdlItemName")
    val ddlItemName: String,

    @SerializedName("UserId")
    val userId: Int,

    @SerializedName("UserName")
    val userName: String,

    @SerializedName("DeparmentId")
    val departmentId: Int,

    @SerializedName("DesignationId")
    val designationId: Int,

    @SerializedName("SectionId")
    val sectionId: Int
)