package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the content layout
        setContentView(R.layout.activity_main)

        // set the nav controller for the nav host
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        // configure appbar to not have back button
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment,
                R.id.coursesFragment,
                R.id.settingsFragment,
                R.id.loginFragment
            )
        )

        // find toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // connects the toolbar to the nav controller
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // assign and set bottom nav controller
        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottom_nav.setupWithNavController(navController)

        // listener to check destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.profileFragment) {
                bottom_nav.visibility =
                    View.GONE // Hide the bottom navigation for the LoginFragment
            } else {
                bottom_nav.visibility =
                    View.VISIBLE // Show the bottom navigation for other fragments
            }
        }
    }

    // allow the up button to work
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}