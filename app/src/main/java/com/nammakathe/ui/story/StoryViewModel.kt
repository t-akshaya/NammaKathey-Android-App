package com.nammakathe.ui.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val repository: NammaKatheyRepository) : ViewModel() {
    val isKannada = repository.isKannada
    val userId = repository.userId

    fun getHero(id: String) = repository.getHeroById(id)
    fun setKannada(kn: Boolean) = viewModelScope.launch { repository.setIsKannada(kn) }

    fun saveProgress(heroId: String, userId: String, page: Int, completed: Boolean) =
        viewModelScope.launch { repository.saveProgress(heroId, userId, page, completed) }

    fun toggleFavorite(heroId: String, userId: String) = viewModelScope.launch {
        val progress = repository.getAllProgressForUser(userId)
        // We toggle - simplified here
        repository.toggleFavorite(heroId, userId, true)
    }
}
