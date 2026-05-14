package com.nammakathe.core.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.nammakathe.core.domain.model.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
    private var cachedData: HeroDataRoot? = null

    private fun getData(): HeroDataRoot {
        if (cachedData != null) return cachedData!!
        return try {
            val json = context.assets.open("heroes_data.json").bufferedReader().readText()
            gson.fromJson(json, HeroDataRoot::class.java).also { cachedData = it }
        } catch (e: Exception) {
            HeroDataRoot(emptyList(), emptyList(), emptyList())
        }
    }

    fun getAllDistricts(): List<District> = getData().districts.map { it.toDomain() }

    fun getAllHeroes(): List<Hero> = getData().heroes.map { it.toDomain() }

    fun getHeroesByDistrict(districtId: String): List<Hero> =
        getAllHeroes().filter { it.districtId == districtId }

    fun getHeroById(heroId: String): Hero? =
        getAllHeroes().find { it.id == heroId }

    fun getAllBadges(): List<HeroBadge> = getData().badges.map { it.toDomain() }

    fun getDailyHero(): Hero? = getAllHeroes().shuffled().firstOrNull()

    fun searchHeroes(query: String): List<Hero> {
        val q = query.lowercase()
        return getAllHeroes().filter {
            it.name.lowercase().contains(q) ||
            it.nameKn.contains(q) ||
            it.districtName.lowercase().contains(q) ||
            it.category.label.lowercase().contains(q)
        }
    }
}

// JSON DTOs
data class HeroDataRoot(
    @SerializedName("districts") val districts: List<DistrictDto>,
    @SerializedName("heroes") val heroes: List<HeroDto>,
    @SerializedName("badges") val badges: List<BadgeDto>
)

data class DistrictDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nameKn") val nameKn: String,
    @SerializedName("region") val region: String,
    @SerializedName("heroCount") val heroCount: Int,
    @SerializedName("primaryColor") val primaryColor: String,
    @SerializedName("secondaryColor") val secondaryColor: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
    @SerializedName("mapX") val mapX: Float,
    @SerializedName("mapY") val mapY: Float
) {
    fun toDomain() = District(id, name, nameKn, region, heroCount, primaryColor, secondaryColor, lat, lng, mapX, mapY)
}

data class HeroDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nameKn") val nameKn: String,
    @SerializedName("title") val title: String,
    @SerializedName("titleKn") val titleKn: String,
    @SerializedName("districtId") val districtId: String,
    @SerializedName("districtName") val districtName: String,
    @SerializedName("era") val era: String,
    @SerializedName("category") val category: String,
    @SerializedName("emoji") val emoji: String,
    @SerializedName("shortBio") val shortBio: String,
    @SerializedName("shortBioKn") val shortBioKn: String,
    @SerializedName("pages") val pages: List<StoryPageDto>,
    @SerializedName("quiz") val quiz: List<QuizDto>,
    @SerializedName("badgeId") val badgeId: String,
    @SerializedName("memorialName") val memorialName: String,
    @SerializedName("memorialLat") val memorialLat: Double,
    @SerializedName("memorialLng") val memorialLng: Double
) {
    fun toDomain() = Hero(
        id = id, name = name, nameKn = nameKn, title = title, titleKn = titleKn,
        districtId = districtId, districtName = districtName, era = era,
        category = HeroCategory.valueOf(category),
        emoji = emoji, shortBio = shortBio, shortBioKn = shortBioKn,
        pages = pages.map { it.toDomain() },
        quiz = quiz.map { it.toDomain() },
        badgeId = badgeId, memorialName = memorialName,
        memorialLat = memorialLat, memorialLng = memorialLng
    )
}

data class StoryPageDto(
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("title") val title: String,
    @SerializedName("titleKn") val titleKn: String,
    @SerializedName("content") val content: String,
    @SerializedName("contentKn") val contentKn: String,
    @SerializedName("illustrationEmoji") val illustrationEmoji: String,
    @SerializedName("bgGradientStart") val bgGradientStart: String,
    @SerializedName("bgGradientEnd") val bgGradientEnd: String
) {
    fun toDomain() = StoryPage(pageNumber, title, titleKn, content, contentKn, illustrationEmoji, bgGradientStart, bgGradientEnd)
}

data class QuizDto(
    @SerializedName("id") val id: String,
    @SerializedName("question") val question: String,
    @SerializedName("questionKn") val questionKn: String,
    @SerializedName("options") val options: List<String>,
    @SerializedName("optionsKn") val optionsKn: List<String>,
    @SerializedName("correctIndex") val correctIndex: Int,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("explanationKn") val explanationKn: String
) {
    fun toDomain() = QuizQuestion(id, question, questionKn, options, optionsKn, correctIndex, explanation, explanationKn)
}

data class BadgeDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nameKn") val nameKn: String,
    @SerializedName("description") val description: String,
    @SerializedName("descriptionKn") val descriptionKn: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("rarity") val rarity: String
) {
    fun toDomain() = HeroBadge(id, name, nameKn, description, descriptionKn, icon,
        BadgeRarity.valueOf(rarity))
}
