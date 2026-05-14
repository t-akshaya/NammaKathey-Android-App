package com.nammakathe.core.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.nammakathe.core.domain.model.AgeGroup
import com.nammakathe.core.domain.model.Hero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiStoryService @Inject constructor() {

    private val model by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "YOUR_GEMINI_API_KEY" // Replace with BuildConfig.GENAI_API_KEY
        )
    }

    suspend fun generateHeroStory(hero: Hero, ageGroup: AgeGroup, inKannada: Boolean): String {
        val langInstruction = if (inKannada) "Write in simple Kannada language." else "Write in simple English."
        val ageInstruction = when (ageGroup) {
            AgeGroup.KIDS -> "Use very simple words, short sentences, and playful comparisons. The story should be exciting and easy for a 5-8 year old child to understand."
            AgeGroup.TWEENS -> "Use clear language with some interesting facts. Write an engaging narrative for a 9-12 year old student who is learning about history."
            AgeGroup.TEENS -> "Include historical context, causes and effects, and critical thinking points. Write for a 13-16 year old who can appreciate deeper insights."
        }

        val prompt = """
            You are a children's storyteller specializing in Indian history and Karnataka heritage.
            
            Write a short, engaging, inspiring story (3-4 paragraphs) about the historical hero: ${hero.name} (${hero.nameKn}).
            
            Key facts to include:
            - Era: ${hero.era}
            - Title: ${hero.title}
            - District: ${hero.districtName}, Karnataka
            - Category: ${hero.category.label}
            - Brief context: ${hero.shortBio}
            
            Instructions:
            - $ageInstruction
            - $langInstruction
            - Make the story motivational and instill pride in Karnataka's heritage
            - Focus on specific brave or wise acts
            - End with a lesson or value the reader can apply in their own life
            - Keep it under 250 words
            - Do NOT use markdown formatting, just plain paragraphs
        """.trimIndent()

        return try {
            val response = model.generateContent(prompt)
            response.text ?: getFallbackStory(hero, inKannada)
        } catch (e: Exception) {
            getFallbackStory(hero, inKannada)
        }
    }

    private fun getFallbackStory(hero: Hero, inKannada: Boolean): String {
        return if (inKannada) {
            "${hero.nameKn} ಕರ್ನಾಟಕದ ಶ್ರೇಷ್ಠ ವೀರ. ${hero.shortBioKn} ಅವರ ಜೀವನ ನಮ್ಮೆಲ್ಲರಿಗೂ ಸ್ಫೂರ್ತಿ ನೀಡುತ್ತದೆ. ಅವರ ಧೈರ್ಯ ಮತ್ತು ತ್ಯಾಗ ಇಂದಿಗೂ ನಮ್ಮ ಹೃದಯದಲ್ಲಿ ಜೀವಂತ."
        } else {
            "${hero.name} was one of Karnataka's greatest heroes. ${hero.shortBio} Their life continues to inspire us all. Their courage and sacrifice live on in our hearts even today."
        }
    }
}
