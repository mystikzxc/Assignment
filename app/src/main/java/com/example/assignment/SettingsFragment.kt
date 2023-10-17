package com.example.assignment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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