package com.nammakathe.core.data.repository

import com.nammakathe.core.data.local.dao.UserDao
import com.nammakathe.core.data.local.dao.UserProgressDao
import com.nammakathe.core.data.local.database.PreferencesManager
import com.nammakathe.core.data.local.entity.*
import com.nammakathe.core.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NammaKatheyRepository @Inject constructor(

    private val heroDataSource: HeroDataSource,
    private val progressDao: UserProgressDao,
    private val userDao: UserDao,
    private val prefsManager: PreferencesManager

) {

    // HERO DATA
    fun getAllHeroes(): List<Hero> =
        heroDataSource.getAllHeroes()

    fun getAllDistricts(): List<District> =
        heroDataSource.getAllDistricts()

    fun getHeroesByDistrict(
        districtId: String
    ) = heroDataSource.getHeroesByDistrict(districtId)

    fun getHeroById(
        heroId: String
    ) = heroDataSource.getHeroById(heroId)

    fun getDailyHero() =
        heroDataSource.getDailyHero()

    fun getAllBadges() =
        heroDataSource.getAllBadges()

    fun searchHeroes(
        query: String
    ) = heroDataSource.searchHeroes(query)


    // USER PROGRESS
    fun getAllProgressForUser(
        userId: String
    ) = progressDao.getAllProgressForUser(userId)

    fun getCompletedCount(
        userId: String
    ) = progressDao.getCompletedCount(userId)

    fun getFavoriteHeroIds(
        userId: String
    ) = progressDao.getFavoriteHeroIds(userId)

    fun getEarnedBadges(
        userId: String
    ) = progressDao.getEarnedBadges(userId)

    fun getBadgeCount(
        userId: String
    ) = progressDao.getBadgeCount(userId)

    fun getUserProfile(
        userId: String
    ) = progressDao.getUserProfile(userId)

    fun getAverageScore(
        userId: String
    ) = progressDao.getAverageScore(userId)

    fun getQuizResults(
        userId: String
    ) = progressDao.getQuizResultsForUser(userId)


    suspend fun saveProgress(
        heroId: String,
        userId: String,
        page: Int,
        completed: Boolean
    ) {

        val existing =
            progressDao.getProgressForHero(heroId, userId)

        progressDao.upsertProgress(

            UserProgressEntity(
                heroId = heroId,
                userId = userId,
                lastPageRead = page,
                isCompleted = completed ||
                        (existing?.isCompleted == true),
                isFavorite = existing?.isFavorite ?: false,
                quizScore = existing?.quizScore ?: -1,
                lastReadAt = System.currentTimeMillis()
            )
        )
    }


    suspend fun toggleFavorite(
        heroId: String,
        userId: String,
        isFavorite: Boolean
    ) {

        val existing =
            progressDao.getProgressForHero(heroId, userId)

        if (existing == null) {

            progressDao.upsertProgress(
                UserProgressEntity(
                    heroId = heroId,
                    userId = userId,
                    isFavorite = isFavorite
                )
            )

        } else {

            progressDao.updateFavorite(
                heroId,
                userId,
                isFavorite
            )
        }
    }


    suspend fun saveQuizResult(
        heroId: String,
        userId: String,
        score: Int,
        total: Int,
        timeSec: Int,
        badgeId: String
    ) {

        progressDao.insertQuizResult(

            QuizResultEntity(
                heroId = heroId,
                userId = userId,
                score = score,
                totalQuestions = total,
                timeTakenSeconds = timeSec
            )
        )

        if (score >= (total * 0.6).toInt()) {

            if (!progressDao.hasBadge(
                    badgeId,
                    userId
                )
            ) {

                progressDao.upsertBadge(

                    EarnedBadgeEntity(
                        badgeId = badgeId,
                        userId = userId,
                        heroId = heroId,
                        score = score
                    )
                )
            }
        }

        val existing =
            progressDao.getProgressForHero(
                heroId,
                userId
            )

        progressDao.upsertProgress(

            (existing ?: UserProgressEntity(
                heroId = heroId,
                userId = userId
            )).copy(
                quizScore = score,
                isCompleted = true
            )
        )
    }


    suspend fun saveUserProfile(
        id: String,
        name: String,
        ageGroup: String,
        isGuest: Boolean,
        avatarIndex: Int
    ) {

        progressDao.upsertUserProfile(

            UserProfileEntity(
                id = id,
                name = name,
                ageGroup = ageGroup,
                isGuest = isGuest,
                avatarIndex = avatarIndex
            )
        )
    }


    // AUTH
    suspend fun registerUser(
        user: UserEntity
    ) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): UserEntity? {

        return userDao.loginUser(
            email = email.trim(),
            password = password.trim()
        )
    }

    suspend fun logout() {

        prefsManager.setAuthDone(false)
        prefsManager.setUserId("")
        prefsManager.setUserName("")
        prefsManager.setParentPin("1234")
    }


    // DATASTORE
    val userId = prefsManager.userId
    val userName = prefsManager.userName
    val isKannada = prefsManager.isKannada
    val isOnboardingDone = prefsManager.isOnboardingDone
    val isAuthDone = prefsManager.isAuthDone
    val ageGroup = prefsManager.ageGroup
    val isDarkMode = prefsManager.isDarkMode
    val isSoundEnabled = prefsManager.isSoundEnabled
    val avatarIndex = prefsManager.avatarIndex

    suspend fun setUserId(id: String) =
        prefsManager.setUserId(id)

    suspend fun setUserName(name: String) =
        prefsManager.setUserName(name)

    suspend fun setAgeGroup(g: String) =
        prefsManager.setAgeGroup(g)

    suspend fun setIsKannada(kn: Boolean) =
        prefsManager.setIsKannada(kn)

    suspend fun setOnboardingDone(d: Boolean) =
        prefsManager.setOnboardingDone(d)

    suspend fun setAuthDone(d: Boolean) =
        prefsManager.setAuthDone(d)

    suspend fun setDarkMode(d: Boolean) =
        prefsManager.setDarkMode(d)

    suspend fun setSoundEnabled(e: Boolean) =
        prefsManager.setSoundEnabled(e)

    val parentPin = prefsManager.parentPin

    suspend fun setAvatarIndex(i: Int) =
        prefsManager.setAvatarIndex(i)

    suspend fun setParentPin(pin: String) =
        prefsManager.setParentPin(pin)
}