package com.nammakathe.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: String,
    val name: String,
    val nameKn: String,
    val title: String,
    val titleKn: String,
    val districtId: String,
    val districtName: String,
    val era: String,
    val category: HeroCategory,
    val emoji: String,
    val shortBio: String,
    val shortBioKn: String,
    val pages: List<StoryPage>,
    val quiz: List<QuizQuestion>,
    val badgeId: String,
    val memorialName: String,
    val memorialLat: Double,
    val memorialLng: Double,
    val isFavorite: Boolean = false,
    val isCompleted: Boolean = false
) : Parcelable

@Parcelize
data class StoryPage(
    val pageNumber: Int,
    val title: String,
    val titleKn: String,
    val content: String,
    val contentKn: String,
    val illustrationEmoji: String,
    val bgGradientStart: String,
    val bgGradientEnd: String
) : Parcelable

@Parcelize
data class QuizQuestion(
    val id: String,
    val question: String,
    val questionKn: String,
    val options: List<String>,
    val optionsKn: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val explanationKn: String
) : Parcelable

enum class HeroCategory(val label: String, val labelKn: String, val emoji: String) {
    FREEDOM_FIGHTER("Freedom Fighter", "ಸ್ವಾತಂತ್ರ್ಯ ಹೋರಾಟಗಾರ", "⚔️"),
    POET("Poet & Writer", "ಕವಿ ಮತ್ತು ಲೇಖಕ", "📜"),
    SOCIAL_REFORMER("Social Reformer", "ಸಮಾಜ ಸುಧಾರಕ", "🌟"),
    SCIENTIST("Scientist & Engineer", "ವಿಜ್ಞಾನಿ ಮತ್ತು ಇಂಜಿನಿಯರ್", "🔬"),
    KING("King & Ruler", "ರಾಜ ಮತ್ತು ಆಡಳಿತಗಾರ", "👑")
}

@Parcelize
data class District(
    val id: String,
    val name: String,
    val nameKn: String,
    val region: String,
    val heroCount: Int,
    val primaryColor: String,
    val secondaryColor: String,
    val lat: Double,
    val lng: Double,
    val mapX: Float,
    val mapY: Float
) : Parcelable
