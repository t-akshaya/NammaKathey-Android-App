package com.nammakathe.core.data.local.database

import com.nammakathe.core.data.local.dao.UserDao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.nammakathe.core.data.local.dao.UserProgressDao
import com.nammakathe.core.data.local.entity.*

@Database(
    entities = [
        UserProgressEntity::class,
        EarnedBadgeEntity::class,
        UserProfileEntity::class,
        QuizResultEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class NammaKatheyDatabase : RoomDatabase() {
    abstract fun userProgressDao(): UserProgressDao

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "namma_kathey.db"
    }
}
