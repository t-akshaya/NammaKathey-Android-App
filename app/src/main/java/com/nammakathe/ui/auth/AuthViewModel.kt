package com.nammakathe.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nammakathe.core.data.local.entity.UserEntity
import com.nammakathe.core.data.repository.NammaKatheyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: NammaKatheyRepository
) : ViewModel() {

    fun saveUser(
        userId: String,
        name: String,
        email: String,
        password: String,
        ageGroup: String,
        isGuest: Boolean,
        avatarIndex: Int
    ) {

        viewModelScope.launch {

            repository.registerUser(

                UserEntity(
                    id = userId,
                    fullName = name,
                    email = email,
                    password = password,
                    ageGroup = ageGroup,
                    isGuest = isGuest,
                    avatarIndex = avatarIndex
                )
            )

            repository.saveUserProfile(
                id = userId,
                name = name,
                ageGroup = ageGroup,
                isGuest = isGuest,
                avatarIndex = avatarIndex
            )

            // SAVE TO DATASTORE
            repository.setUserId(
                userId
            )

            repository.setUserName(
                name
            )

            repository.setAvatarIndex(
                avatarIndex
            )

            repository.setAuthDone(
                true
            )
        }
    }

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {

        viewModelScope.launch {

            val user =
                repository.loginUser(
                    email.trim(),
                    password.trim()
                )

            if (user != null) {

                repository.setUserId(
                    user.id
                )

                repository.setUserName(
                    user.fullName
                )

                // RESTORE AVATAR
                repository.setAvatarIndex(
                    user.avatarIndex
                )

                repository.setAuthDone(
                    true
                )

                onSuccess()

            } else {

                onFailure()
            }
        }
    }
}