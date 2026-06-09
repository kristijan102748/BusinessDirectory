package com.example.businessdirectory.model

data class Company(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val email: String = "",
    val phone: String = "",
    val website: String = "",
    val categories: String = ""   // пример: "Services,Industry"
)