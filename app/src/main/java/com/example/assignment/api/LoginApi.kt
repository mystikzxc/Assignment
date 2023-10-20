package com.example.assignment.api

import com.example.assignment.model.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("api/login")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): LoginResponse
}