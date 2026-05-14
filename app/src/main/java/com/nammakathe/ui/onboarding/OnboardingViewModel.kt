package com.nammakathe.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val repository: NammaKatheyRepository) : ViewModel() {
    fun setOnboardingDone() = viewModelScope.launch { repository.setOnboardingDone(true) }
}
