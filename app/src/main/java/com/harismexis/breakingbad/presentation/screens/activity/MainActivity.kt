package com.harismexis.breakingbad.presentation.screens.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.ActivityMainBinding
import com.harismexis.breakingbad.framework.util.extensions.makeStatusBarDark

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarDark()
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupBottomNav(navController)
        setupDestinationListener(navController)
    }

    private fun setupBottomNav(navController: NavController) {
        binding.bottomNavView.apply() {
            setupWithNavController(navController)
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.quotes_dest -> {
                        navController.navigate(R.id.quotes_dest)
                    }
                    R.id.deaths_dest -> {
                        navController.navigate(R.id.deaths_dest)
                    }
                    R.id.episodes_dest -> {
                        navController.navigate(R.id.episodes_dest)
                    }
                }
                true
            }
        }

    }

    private fun setupDestinationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home_dest) {
                binding.bottomNavView.visibility = View.VISIBLE
            } else {
                binding.bottomNavView.visibility = View.GONE
            }

            requestedOrientation = if (destination.id == R.id.player_dest) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }

        }
    }

}