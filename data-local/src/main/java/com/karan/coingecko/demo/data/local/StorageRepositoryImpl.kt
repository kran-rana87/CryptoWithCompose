package com.karan.coingecko.demo.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.karan.coingecko.demo.data.local.StorageRepositoryImpl.PreferencesKeys.FAV_COINS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class StorageRepositoryImpl @Inject constructor(@ApplicationContext val appContext: Context) :
    StorageRepostory {

    private object PreferencesKeys {
        val IS_LOGGED_IN = androidx.datastore.preferences.core.booleanPreferencesKey("is_logged_in")
        val FAV_COINS = androidx.datastore.preferences.core.stringSetPreferencesKey("fav_coins")

    }

    val isUserLoggedInFlow: Flow<Boolean> = appContext.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(androidx.datastore.preferences.core.emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    val favCoins: Flow<List<String>> = appContext.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(androidx.datastore.preferences.core.emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferencesKeys.FAV_COINS] as List<String> ?: emptyList<String>()
        }

    override suspend fun updateLoginState(isLoggedIn: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun updateFavCoins(coinId: String) {
    }

    override fun fetchFavCoins(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun fetchUserLoginState() =
        isUserLoggedInFlow

    private fun mapUserPreferences(preferences: androidx.datastore.preferences.core.Preferences): Boolean {
        return preferences[PreferencesKeys.IS_LOGGED_IN] ?: false
    }
}