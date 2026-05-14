package com.nammakathe.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: NammaKatheyRepository) : ViewModel() {
    val isKannada = repository.isKannada
    val userId = repository.userId

    fun getHero(id: String) = repository.getHeroById(id)

    fun saveQuizResult(heroId: String, userId: String, score: Int, total: Int, timeSec: Int, badgeId: String) =
        viewModelScope.launch { repository.saveQuizResult(heroId, userId, score, total, timeSec, badgeId) }
}
