package com.example.finflow.core.datastore.session

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {

    val ACCESS_TOKEN = stringPreferencesKey("access_token")

    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

    val USER_ID = longPreferencesKey("user_id")
}