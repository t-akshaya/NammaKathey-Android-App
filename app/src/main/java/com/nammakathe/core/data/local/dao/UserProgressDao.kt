package com.nammakathe.core.data.local.dao

import androidx.room.*
import com.nammakathe.core.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {

    // User Progress
    @Upsert
    suspend fun upsertProgress(progress: UserProgressEntity)

    @Query("SELECT * FROM user_progress WHERE userId = :userId")
    fun getAllProgressForUser(userId: String): Flow<List<UserProgressEntity>>

    @Query("SELECT * FROM user_progress WHERE heroId = :heroId AND userId = :userId")
    suspend fun getProgressForHero(heroId: String, userId: String): UserProgressEntity?

    @Query("SELECT COUNT(*) FROM user_progress WHERE userId = :userId AND isCompleted = 1")
    fun getCompletedCount(userId: String): Flow<Int>

    @Query("SELECT heroId FROM user_progress WHERE userId = :userId AND isFavorite = 1")
    fun getFavoriteHeroIds(userId: String): Flow<List<String>>

    @Query("UPDATE user_progress SET isFavorite = :isFavorite WHERE heroId = :heroId AND userId = :userId")
    suspend fun updateFavorite(heroId: String, userId: String, isFavorite: Boolean)

    // Earned Badges
    @Upsert
    suspend fun upsertBadge(badge: EarnedBadgeEntity)

    @Query("SELECT * FROM earned_badges WHERE userId = :userId")
    fun getEarnedBadges(userId: String): Flow<List<EarnedBadgeEntity>>

    @Query("SELECT COUNT(*) FROM earned_badges WHERE userId = :userId")
    fun getBadgeCount(userId: String): Flow<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM earned_badges WHERE badgeId = :badgeId AND userId = :userId)")
    suspend fun hasBadge(badgeId: String, userId: String): Boolean

    // User Profile
    @Upsert
    suspend fun upsertUserProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM user_profile WHERE id = :userId")
    fun getUserProfile(userId: String): Flow<UserProfileEntity?>

    // Quiz Results
    @Insert
    suspend fun insertQuizResult(result: QuizResultEntity)

    @Query("SELECT * FROM quiz_results WHERE userId = :userId ORDER BY takenAt DESC")
    fun getQuizResultsForUser(userId: String): Flow<List<QuizResultEntity>>

    @Query("SELECT AVG(score * 100.0 / totalQuestions) FROM quiz_results WHERE userId = :userId")
    fun getAverageScore(userId: String): Flow<Float?>

    @Query("SELECT SUM(totalReadingMinutes) FROM user_profile WHERE id = :userId")
    fun getTotalReadingMinutes(userId: String): Flow<Int?>
}
