package com.nammakathe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammakathe.core.data.repository.AiStoryService
import com.nammakathe.core.data.repository.NammaKatheyRepository
import com.nammakathe.core.domain.model.AgeGroup
import com.nammakathe.core.domain.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NammaKatheyRepository,
    private val aiStoryService: AiStoryService
) : ViewModel() {

    val isKannada = repository.isKannada
    val userId = repository.userId
    val userName = repository.userName
    val avatarIndex = repository.avatarIndex

    val dailyHero: Hero?
        get() = repository.getDailyHero()

    fun getAllHeroes() =
        repository.getAllHeroes()

    fun getAllDistricts() =
        repository.getAllDistricts()

    fun getHeroesByDistrict(
        districtId: String
    ) = repository.getHeroesByDistrict(
        districtId
    )

    fun getHeroById(
        heroId: String
    ) = repository.getHeroById(
        heroId
    )

    fun searchHeroes(
        query: String
    ) = repository.searchHeroes(
        query
    )

    fun getAllBadges() =
        repository.getAllBadges()

    fun getCompletedCount(
        userId: String
    ) = repository.getCompletedCount(
        userId
    )

    fun getBadgeCount(
        userId: String
    ) = repository.getBadgeCount(
        userId
    )

    fun getEarnedBadges(
        userId: String
    ) = repository.getEarnedBadges(
        userId
    )

    fun getFavoriteHeroIds(
        userId: String
    ) = repository.getFavoriteHeroIds(
        userId
    )

    fun getAverageScore(
        userId: String
    ) = repository.getAverageScore(
        userId
    )

    fun getQuizResults(
        userId: String
    ) = repository.getQuizResults(
        userId
    )

    fun setKannada(
        kn: Boolean
    ) = viewModelScope.launch {

        repository.setIsKannada(
            kn
        )
    }

    fun toggleFavorite(
        heroId: String,
        userId: String,
        isFavorite: Boolean
    ) {

        viewModelScope.launch {

            repository.toggleFavorite(
                heroId,
                userId,
                isFavorite
            )
        }
    }

    suspend fun generateAiStory(
        hero: Hero,
        ageGroup: AgeGroup,
        inKannada: Boolean
    ): String {

        return aiStoryService.generateHeroStory(
            hero,
            ageGroup,
            inKannada
        )
    }

    fun setDarkMode(
        dark: Boolean
    ) = viewModelScope.launch {

        repository.setDarkMode(
            dark
        )
    }

    fun setSoundEnabled(
        enabled: Boolean
    ) = viewModelScope.launch {

        repository.setSoundEnabled(
            enabled
        )
    }

    // PARENT PIN
    fun saveParentPin(
        pin: String
    ) = viewModelScope.launch {

        repository.setParentPin(
            pin
        )
    }

    suspend fun getSavedParentPin(): String {

        return repository.parentPin
            .first()
    }

    suspend fun verifyParentPin(
        enteredPin: String
    ): Boolean {

        val savedPin =
            repository.parentPin
                .first()

        return enteredPin ==
                savedPin
    }

    // LOGOUT
    fun logout() =
        viewModelScope.launch {

            repository.logout()
        }
}