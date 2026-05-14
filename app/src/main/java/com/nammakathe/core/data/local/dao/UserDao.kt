package com.nammakathe.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nammakathe.core.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query(
        "SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1"
    )
    suspend fun loginUser(
        email: String,
        password: String
    ): UserEntity?
}