package com.mbl.deneme_newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mbl.deneme_newsapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val MynavHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment1) as NavHostFragment
        //NavigationUI.setupWithNavController(binding.bottomNavigation1,MynavHostFragment.navController)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment1) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation1.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.headlinesFragment -> {
                    navController.navigate(R.id.headlinesFragment)
                    true
                }
                R.id.favouritesFragment -> {
                    navController.navigate(R.id.favouritesFragment)
                    true
                }
                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                else -> false
            }
        }
    }



}