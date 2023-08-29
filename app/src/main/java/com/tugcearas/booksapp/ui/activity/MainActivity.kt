package com.tugcearas.booksapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tugcearas.booksapp.R
import com.tugcearas.booksapp.databinding.ActivityMainBinding
import com.tugcearas.booksapp.util.extensions.gone
import com.tugcearas.booksapp.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController : NavController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination,_ ->
            when(destination.id){
                R.id.splashScreen -> {
                    window.statusBarColor  = ContextCompat.getColor(this,R.color.white)
                }
                else -> {
                    window.statusBarColor = ContextCompat.getColor(this,R.color.main_color)
                }
            }
        }
    }
}