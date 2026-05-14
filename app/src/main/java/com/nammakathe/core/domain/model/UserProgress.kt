package com.nammakathe.core.domain.model

data class UserProfile(
    val id: String,
    val name: String,
    val ageGroup: AgeGroup,
    val isGuest: Boolean,
    val email: String = "",
    val avatarIndex: Int = 0,
    val totalBadges: Int = 0,
    val totalStoriesRead: Int = 0,
    val totalQuizzesTaken: Int = 0,
    val averageQuizScore: Float = 0f,
    val totalReadingMinutes: Int = 0,
    val favoriteHeroIds: List<String> = emptyList(),
    val completedHeroIds: List<String> = emptyList(),
    val earnedBadgeIds: List<String> = emptyList()
)

enum class AgeGroup(val label: String, val description: String) {
    KIDS("Age 5–8", "Little Explorer"),
    TWEENS("Age 9–12", "Young Learner"),
    TEENS("Age 13–16", "Heritage Scholar")
}

data class HeroBadge(
    val id: String,
    val name: String,
    val nameKn: String,
    val description: String,
    val descriptionKn: String,
    val icon: String,
    val rarity: BadgeRarity,
    val isEarned: Boolean = false,
    val earnedDate: Long = 0L
)

enum class BadgeRarity(val color: String, val label: String) {
    COMMON("#CD7F32", "Bronze"),
    RARE("#C0C0C0", "Silver"),
    EPIC("#FFD700", "Gold"),
    LEGENDARY("#FF6B35", "Legendary")
}
