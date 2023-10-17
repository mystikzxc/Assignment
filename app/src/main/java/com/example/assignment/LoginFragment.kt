package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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

        // find the forgot_pw_text by its ID
        view.findViewById<TextView>(R.id.forgot_pw_text).setOnClickListener {
            // create a Toast with text, to appear for a short time
            val forgotToast = Toast.makeText(context, "Are you dumb?", Toast.LENGTH_SHORT)
            // show the Toast
            forgotToast.show()
        }
    }
}