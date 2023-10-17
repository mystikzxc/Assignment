package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val args: DashboardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // assign username to the username text view
        view.findViewById<TextView>(R.id.username_text_view).text = args.username

        // find the login button
        val profile_button = view.findViewById<AppCompatImageButton>(R.id.profile_img_button)

        // when login button is pressed,
        profile_button.setOnClickListener {

            // navigate to Profile
            val action = DashboardFragmentDirections.actionDashboardFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }
}