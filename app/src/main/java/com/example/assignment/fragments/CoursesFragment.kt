package com.example.assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.assignment.R
import com.example.assignment.api.CoursesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class CoursesFragment : Fragment(R.layout.fragment_courses){

    // assign courses list
    private val coursesListTextView: TextView by lazy {
        requireView().findViewById<TextView>(R.id.courses_list)
    }

    // create retrofit object using url
    private val retrofitObject by lazy {
        Retrofit.Builder()
            .baseUrl("https://59f1-2405-6e00-d79-3e00-207c-1d80-3aa1-69ae.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // initialise courses data
    private val coursesLiveData = MutableLiveData<List<String>?>(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialise api
        val api = retrofitObject.create(CoursesApi::class.java)

        // observe the courses livedata
        coursesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let { courses ->
                coursesListTextView.text = courses.toString()
            }
        }

        // update the the courses live data with the result
        CoroutineScope(Dispatchers.Main).launch {
            try {
                coursesLiveData.value = api.getCourses().courses
            } catch (e: Exception) {
                Log.e("CoursesFragment", "Error fetching courses: ${e.message}")
            }
        }
    }
}