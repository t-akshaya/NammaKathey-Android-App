package com.nammakathe.di

import android.content.Context
import androidx.room.Room
import com.nammakathe.core.data.local.dao.UserProgressDao
import com.nammakathe.core.data.local.database.NammaKatheyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.nammakathe.core.data.local.dao.UserDao

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NammaKatheyDatabase =
        Room.databaseBuilder(context, NammaKatheyDatabase::class.java, NammaKatheyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(
        database: NammaKatheyDatabase
    ): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserProgressDao(db: NammaKatheyDatabase): UserProgressDao = db.userProgressDao()
}
