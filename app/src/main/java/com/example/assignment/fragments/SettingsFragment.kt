package com.example.assignment.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.assignment.R

class SettingsFragment : Fragment(R.layout.fragment_settings){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the logout button
        val logout_button = view.findViewById<AppCompatButton>(R.id.logout_button)

        // when login button is pressed,
        logout_button.setOnClickListener {

            // navigate to Profile
            val action = SettingsFragmentDirections.actionSettingsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}