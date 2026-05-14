package com.nammakathe.ui.badge

import androidx.lifecycle.ViewModel
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor(private val repository: NammaKatheyRepository) : ViewModel() {
    val isKannada = repository.isKannada
    val userId = repository.userId

    fun getHero(id: String) = repository.getHeroById(id)
    fun getAllBadges() = repository.getAllBadges()
    fun getEarnedBadges(userId: String) = repository.getEarnedBadges(userId)
    fun getBadgeForHero(badgeId: String) = repository.getAllBadges().find { it.id == badgeId }
}
