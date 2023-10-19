package com.example.assignment.api

import com.example.assignment.model.CoursesList
import retrofit2.http.GET

interface CoursesApi {

    @GET("api/courses")
    suspend fun getCourses(): CoursesList
}