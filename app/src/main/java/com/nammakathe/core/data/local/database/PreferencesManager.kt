package com.nammakathe.core.data.local.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore:
        DataStore<Preferences>
        by preferencesDataStore(
            name = "namma_kathey_prefs"
        )

@Singleton
class PreferencesManager @Inject constructor(

    @ApplicationContext
    private val context: Context

) {

    private val dataStore =
        context.dataStore

    companion object {

        val KEY_USER_ID =
            stringPreferencesKey("user_id")

        val KEY_USER_NAME =
            stringPreferencesKey("user_name")

        val KEY_AGE_GROUP =
            stringPreferencesKey("age_group")

        val KEY_IS_GUEST =
            booleanPreferencesKey("is_guest")

        val KEY_IS_KANNADA =
            booleanPreferencesKey("is_kannada")

        val KEY_ONBOARDING_DONE =
            booleanPreferencesKey("onboarding_done")

        val KEY_AUTH_DONE =
            booleanPreferencesKey("auth_done")

        val KEY_DARK_MODE =
            booleanPreferencesKey("dark_mode")

        val KEY_SOUND_ENABLED =
            booleanPreferencesKey("sound_enabled")

        val KEY_AVATAR_INDEX =
            intPreferencesKey("avatar_index")

        val KEY_PARENT_PIN =
            stringPreferencesKey("parent_pin")
    }

    val userId: Flow<String> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_USER_ID] ?: "guest"
            }

    val userName: Flow<String> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_USER_NAME]
                    ?: "Young Explorer"
            }

    val isKannada: Flow<Boolean> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_IS_KANNADA]
                    ?: false
            }

    val isOnboardingDone: Flow<Boolean> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_ONBOARDING_DONE]
                    ?: false
            }

    val isAuthDone: Flow<Boolean> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_AUTH_DONE]
                    ?: false
            }

    val ageGroup: Flow<String> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_AGE_GROUP]
                    ?: "TWEENS"
            }

    val isDarkMode: Flow<Boolean> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_DARK_MODE]
                    ?: false
            }

    val isSoundEnabled: Flow<Boolean> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_SOUND_ENABLED]
                    ?: true
            }

    val avatarIndex: Flow<Int> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_AVATAR_INDEX]
                    ?: 0
            }

    val parentPin: Flow<String> =
        dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                it[KEY_PARENT_PIN]
                    ?: "1234"
            }

    suspend fun setUserId(id: String) =
        dataStore.edit {
            it[KEY_USER_ID] = id
        }

    suspend fun setUserName(name: String) =
        dataStore.edit {
            it[KEY_USER_NAME] = name
        }

    suspend fun setAgeGroup(group: String) =
        dataStore.edit {
            it[KEY_AGE_GROUP] = group
        }

    suspend fun setIsGuest(guest: Boolean) =
        dataStore.edit {
            it[KEY_IS_GUEST] = guest
        }

    suspend fun setIsKannada(kn: Boolean) =
        dataStore.edit {
            it[KEY_IS_KANNADA] = kn
        }

    suspend fun setOnboardingDone(done: Boolean) =
        dataStore.edit {
            it[KEY_ONBOARDING_DONE] = done
        }

    suspend fun setAuthDone(done: Boolean) =
        dataStore.edit {
            it[KEY_AUTH_DONE] = done
        }

    suspend fun setDarkMode(dark: Boolean) =
        dataStore.edit {
            it[KEY_DARK_MODE] = dark
        }

    suspend fun setSoundEnabled(enabled: Boolean) =
        dataStore.edit {
            it[KEY_SOUND_ENABLED] = enabled
        }

    suspend fun setAvatarIndex(index: Int) =
        dataStore.edit {
            it[KEY_AVATAR_INDEX] = index
        }

    suspend fun setParentPin(pin: String) =
        dataStore.edit {
            it[KEY_PARENT_PIN] = pin
        }
}