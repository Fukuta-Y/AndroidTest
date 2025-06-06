package com.example.myfirstapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/RecipeAllergyInfo")
    fun getAllergyInfo(@Query("recipeId") recipeId: Int): Call<AllergyResponse>
}
