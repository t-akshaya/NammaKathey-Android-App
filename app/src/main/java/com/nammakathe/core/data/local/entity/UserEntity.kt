package com.nammakathe.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    val id: String,

    val fullName: String,

    val email: String,

    val password: String,

    val ageGroup: String,

    val isGuest: Boolean = false,

    val avatarIndex: Int = 0
)