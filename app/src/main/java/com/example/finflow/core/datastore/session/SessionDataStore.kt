package com.example.finflow.core.datastore.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.finflow.core.datastore.session.model.StoredSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveSession(session: StoredSession) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] = session.accessToken
            preferences[PreferencesKeys.REFRESH_TOKEN] = session.refreshToken
            preferences[PreferencesKeys.USER_ID] = session.userId
        }
    }

    fun observeSession(): Flow<StoredSession?> {
        return dataStore.data.map { preferences ->

            val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN]
            val refreshToken = preferences[PreferencesKeys.REFRESH_TOKEN]
            val userId = preferences[PreferencesKeys.USER_ID]
            if (
                accessToken == null ||
                refreshToken == null ||
                userId == null
            ) {
                null
            } else {
                StoredSession(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                    userId = userId
                )
            }
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}