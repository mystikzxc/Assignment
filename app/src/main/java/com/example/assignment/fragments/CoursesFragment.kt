package com.example.assignment.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.adapter.CourseCatalogAdapter
import com.example.assignment.api.CoursesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class CoursesFragment : Fragment(R.layout.fragment_courses){

    // create retrofit object using url
    private val retrofitObject by lazy {
        Retrofit.Builder()
            .baseUrl("https://0523-122-148-195-192.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // initialise courses data
    private val coursesLiveData = MutableLiveData<List<String>?>(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialise api
        val api = retrofitObject.create(CoursesApi::class.java)

        // update the the courses live data with the result
        CoroutineScope(Dispatchers.Main).launch {
            try {
                coursesLiveData.value = api.getCourses().courses
            } catch (e: Exception) {
                Log.e("CoursesFragment", "Error fetching courses: ${e.message}")
            }
        }

        // find recycler view and assign to adapter of recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.courses_recycler_view)
        val adapter = CourseCatalogAdapter(requireContext(), mutableListOf()) // start with empty list

        // set the adapter
        recyclerView.adapter = adapter

        // set layout manager of recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // observe courses live data
        coursesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let { courses ->
                // update adapter with list
                adapter.setData(courses)
            }
        }

        // initialise search input
        val searchInput = view.findViewById<EditText>(R.id.search_input)

        // capture the text for the search input
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called as the text is changing
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                adapter.filter(query)
            }
        })
    }
}