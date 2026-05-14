package com.nammakathe.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val heroId: String,
    val userId: String,
    val lastPageRead: Int = 0,
    val isCompleted: Boolean = false,
    val isFavorite: Boolean = false,
    val quizScore: Int = -1,
    val readingTimeSeconds: Int = 0,
    val lastReadAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "earned_badges")
data class EarnedBadgeEntity(
    @PrimaryKey val badgeId: String,
    val userId: String,
    val heroId: String,
    val earnedAt: Long = System.currentTimeMillis(),
    val score: Int = 0
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String = "",
    val ageGroup: String = "TWEENS",
    val isGuest: Boolean = false,
    val avatarIndex: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val totalReadingMinutes: Int = 0
)

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val heroId: String,
    val userId: String,
    val score: Int,
    val totalQuestions: Int,
    val timeTakenSeconds: Int,
    val takenAt: Long = System.currentTimeMillis()
)
