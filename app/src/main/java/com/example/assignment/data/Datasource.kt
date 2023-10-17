package com.example.assignment.data

import com.example.assignment.R
import com.example.assignment.model.RecommendedCourses

class Datasource {
    fun loadRecommendedCourses(): List<RecommendedCourses> {
        return listOf<RecommendedCourses>(
            RecommendedCourses(R.string.recommended_1),
            RecommendedCourses(R.string.recommended_2),
            RecommendedCourses(R.string.recommended_3),
            RecommendedCourses(R.string.recommended_4)
        )
    }
}