package com.example.medical_tab.model

data class TokenRaiseRequest(
    val IdCardNo: String,
    val LineId: String,
    val UrgencyType: Int
)
