package com.nammakathe.ui.splash

import androidx.lifecycle.ViewModel
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: NammaKatheyRepository
) : ViewModel() {
    val isOnboardingDone = repository.isOnboardingDone
    val isAuthDone = repository.isAuthDone
}
