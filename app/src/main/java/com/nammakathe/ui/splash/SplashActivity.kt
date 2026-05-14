package com.nammakathe.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nammakathe.R
import com.nammakathe.databinding.ActivitySplashBinding
import com.nammakathe.ui.auth.AuthActivity
import com.nammakathe.ui.home.MainActivity
import com.nammakathe.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAnimations()

        Handler(Looper.getMainLooper()).postDelayed({ navigateNext() }, 2800)
    }

    private fun startAnimations() {
        binding.tvLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_scale_in))
        binding.tvAppName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up_fade))
        binding.tvTagline.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_delay))
        binding.lottieSplash.playAnimation()
    }

    private fun navigateNext() {
        lifecycleScope.launch {
            val onboarded = viewModel.isOnboardingDone.first()
            val authDone = viewModel.isAuthDone.first()
            val intent = when {
                !onboarded -> Intent(this@SplashActivity, OnboardingActivity::class.java)
                !authDone -> Intent(this@SplashActivity, AuthActivity::class.java)
                else -> Intent(this@SplashActivity, MainActivity::class.java)
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
