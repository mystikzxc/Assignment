package com.example.assignment.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.assignment.R

class ProfileFragment : Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the save_button by its ID
        view.findViewById<AppCompatButton>(R.id.save_button).setOnClickListener {
            // create a Toast with text, to appear for a short time
            val saveToast = Toast.makeText(context, "Saved Changes", Toast.LENGTH_SHORT)
            // show the Toast
            saveToast.show()
        }
    }
}