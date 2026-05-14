package com.nammakathe.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nammakathe.R
import com.nammakathe.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupLanguageToggle()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.districtFragment, R.id.badgeFragment, R.id.profileFragment ->
                    binding.bottomNav.visibility = android.view.View.VISIBLE
                else -> binding.bottomNav.visibility = android.view.View.GONE
            }
        }
    }

    private fun setupLanguageToggle() {
        lifecycleScope.launch {
            viewModel.isKannada.collect { isKn ->
                binding.fabLanguage.text = if (isKn) "EN" else "ಕನ್ನಡ"
            }
        }
        binding.fabLanguage.setOnClickListener {
            lifecycleScope.launch {
                val current = viewModel.isKannada.first()
                viewModel.setKannada(!current)
            }
        }
    }

    fun isKannada() = viewModel.isKannada
}
