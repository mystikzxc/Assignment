package com.example.assignment.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.adapter.RecommendedCoursesAdapter
import com.example.assignment.data.Datasource

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val args: DashboardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = args.username
        val usernameText = getString(R.string.username_text, username)
        view.findViewById<TextView>(R.id.username_text_view).text = usernameText

        // find the login button
        val profile_button = view.findViewById<AppCompatImageButton>(R.id.profile_img_button)

        // when login button is pressed,
        profile_button.setOnClickListener {

            // navigate to Profile
            val action = DashboardFragmentDirections.actionDashboardFragmentToProfileFragment()
            findNavController().navigate(action)
        }

        // initialise data. store returned list of recommended courses
        val recommendedCoursesDataset = Datasource().loadRecommendedCourses()

        // find recycler view and assign to adapter of recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.recommended_recycler_view)
        recyclerView.adapter = RecommendedCoursesAdapter(requireContext(), recommendedCoursesDataset)

        // improve performance. only use if recyclerview layout size does not change
        recyclerView.setHasFixedSize(true)
    }
}