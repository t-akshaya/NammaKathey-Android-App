package com.nammakathe.core.util

object Constants {
    const val QUIZ_PASS_PERCENTAGE = 60
    const val STORY_READING_TIME_SECONDS = 30
    const val DAILY_REMINDER_HOUR = 9
    const val DAILY_REMINDER_MINUTE = 0
    
    object NavArgs {
        const val HERO_ID = "heroId"
        const val DISTRICT_ID = "districtId"
        const val FROM_QUIZ = "fromQuiz"
    }
    
    object Channels {
        const val DAILY_REMINDER = "daily_hero_reminder"
        const val BADGE_EARNED = "badge_earned"
    }
}
