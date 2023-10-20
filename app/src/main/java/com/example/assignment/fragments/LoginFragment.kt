package com.example.assignment.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.assignment.R
import com.example.assignment.api.LoginApi
import com.example.assignment.model.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginFragment : Fragment(R.layout.fragment_login){

    // initialise login variables
    private var username = ""
    private var password = ""

    // create retrofit object using url
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://ac9f-122-148-195-192.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // initialise api
    private val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    // initialise login data
    private val loginResponseLiveData = MutableLiveData<LoginResponse?>(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observe the login response livedata
        loginResponseLiveData.observe(viewLifecycleOwner) {
            it?.let { response ->
                Toast.makeText(requireContext(), "${response.message}", Toast.LENGTH_LONG).show()
            }
        }

        // find the username input and check if it has changed then turn it into a string
        view.findViewById<EditText>(R.id.username_input).addTextChangedListener {
            username = it.toString()
        }

        // find the password input and check if it has changed then turn it into a string
        view.findViewById<EditText>(R.id.password_input).addTextChangedListener {
            password = it.toString()
        }

        // find the login button
        val login_button = view.findViewById<AppCompatButton>(R.id.login_button)

        // when login button is pressed,
        login_button.setOnClickListener {

            // check the the login response live data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = loginApi.login(username = username, password = password)
                    loginResponseLiveData.value = response

                    if (response.message == "Success") {

                        // assign typed username
                        val username = view.findViewById<EditText>(R.id.username_input).text.toString()

                        // navigate to Dashboard if response is "Success"
                        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(username)
                        findNavController().navigate(action)
                    } else {
                        // case if login Failed
                    }
                } catch (e: Exception) {
                    loginResponseLiveData.value = LoginResponse(message = "Network call failed $e")
                }
            }
        }

        // find the forgot_pw_text by its ID
        view.findViewById<TextView>(R.id.forgot_pw_text).setOnClickListener {
            // create a Toast with text, to appear for a short time
            val forgotToast = Toast.makeText(context, "Oh no! You forgot your password!", Toast.LENGTH_SHORT)
            // show the Toast
            forgotToast.show()
        }
    }
}