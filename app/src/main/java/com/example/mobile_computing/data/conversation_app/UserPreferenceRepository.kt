package com.example.mobile_computing.data.conversation_app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferenceRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val USE_URL_PROFILE = booleanPreferencesKey("use_url_profile")
    }

    val useUrlProfileFlow: Flow<Boolean> = dataStore.data.map {
        it[USE_URL_PROFILE] ?: false
    }


    suspend fun setUseUrlProfile(enabled: Boolean) {
        dataStore.edit { it[USE_URL_PROFILE] = enabled }
    }
}