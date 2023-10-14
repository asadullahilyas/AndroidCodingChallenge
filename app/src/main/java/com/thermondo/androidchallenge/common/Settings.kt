package com.thermondo.androidchallenge.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Settings @Inject constructor(
    context: Context,
) {

    private val dataStore: DataStore<Preferences>

    init {
        dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("settings")
        }
    }

    private val keyBookmarkedLaunches = stringPreferencesKey("BOOKMARKED_LAUNCHES")

    fun getBookmarkedLaunches(): Flow<List<Launch>> {
        return dataStore.data.map { settings ->
            settings[keyBookmarkedLaunches]?.split("|")?.mapNotNull {
                fromJson<Launch>(it)
            } ?: emptyList()
        }
    }

    suspend fun setBookmarkedLaunches(launches: List<Launch>) {
        dataStore.edit { settings ->
            if (launches.isEmpty()) {
                settings.remove(keyBookmarkedLaunches)
            } else {
                settings[keyBookmarkedLaunches] = launches.joinToString("|") { toJson(it) }
            }
        }
    }
}