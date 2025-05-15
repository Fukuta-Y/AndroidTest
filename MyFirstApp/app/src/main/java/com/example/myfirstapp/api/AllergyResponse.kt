package com.example.myfirstapp.api

data class AllergyResponse(
    val result: Result
)

data class Result(
    val success: Boolean,
    val info: List<AllergyInfo>
)

data class AllergyInfo(
    val allergyId: Int
)
