package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.fragment_login){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the login button
        val login_button = view.findViewById<AppCompatButton>(R.id.login_button)

        // when login button is pressed,
        login_button.setOnClickListener {

            // assign typed username
            val username = view.findViewById<TextView>(R.id.username_edit_text).text.toString()

            // navigate to Dashboard
            val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(username)
            findNavController().navigate(action)
        }
    }
}