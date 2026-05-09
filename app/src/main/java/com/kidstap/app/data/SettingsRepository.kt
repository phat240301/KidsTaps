package com.kidstap.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kidstap.app.domain.model.GameConfig
import com.kidstap.app.domain.model.GameMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "kidstap_prefs"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class SettingsRepository(private val context: Context) {

    // Keys
    private object Keys {
        val LANGUAGE = stringPreferencesKey("language")
        val LAST_MODE_SHAPES = stringPreferencesKey("last_mode_shapes")
        val LAST_MODE_ANIMALS = stringPreferencesKey("last_mode_animals")
        val LAST_SPAWN_INTERVAL_SHAPES = floatPreferencesKey("last_spawn_shapes")
        val LAST_HOLD_DURATION_SHAPES = floatPreferencesKey("last_hold_shapes")
        val LAST_TARGET_SCORE_SHAPES = intPreferencesKey("last_target_shapes")
        val LAST_SPAWN_INTERVAL_ANIMALS = floatPreferencesKey("last_spawn_animals")
        val LAST_HOLD_DURATION_ANIMALS = floatPreferencesKey("last_hold_animals")
        val LAST_TARGET_SCORE_ANIMALS = intPreferencesKey("last_target_animals")
        val HIGH_SCORE_SHAPES = intPreferencesKey("high_score_shapes")
        val HIGH_SCORE_ANIMALS = intPreferencesKey("high_score_animals")
        val BGM_ENABLED = booleanPreferencesKey("bgm_enabled")
        val CALM_MODE = booleanPreferencesKey("calm_mode")
    }

    // Language preference
    fun getLanguage(): Flow<String> = context.dataStore.data.map { it[Keys.LANGUAGE] ?: "en" }

    suspend fun setLanguage(lang: String) {
        context.dataStore.edit { it[Keys.LANGUAGE] = lang }
    }

    // Last game config per mode
    fun getLastConfig(mode: GameMode): Flow<GameConfig?> = context.dataStore.data.map { prefs ->
        when (mode) {
            GameMode.SHAPES -> {
                GameConfig(
                    mode = GameMode.SHAPES,
                    childAge = 5,
                    spawnIntervalSeconds = prefs[Keys.LAST_SPAWN_INTERVAL_SHAPES] ?: 4.0f,
                    holdDurationSeconds = prefs[Keys.LAST_HOLD_DURATION_SHAPES] ?: 4.5f,
                    targetScore = prefs[Keys.LAST_TARGET_SCORE_SHAPES] ?: 15,
                    enableBGM = prefs[Keys.BGM_ENABLED] ?: true,
                    calmMode = prefs[Keys.CALM_MODE] ?: false,
                )
            }

            GameMode.ANIMALS -> {
                GameConfig(
                    mode = GameMode.ANIMALS,
                    childAge = 5,
                    spawnIntervalSeconds = prefs[Keys.LAST_SPAWN_INTERVAL_ANIMALS] ?: 4.0f,
                    holdDurationSeconds = prefs[Keys.LAST_HOLD_DURATION_ANIMALS] ?: 4.5f,
                    targetScore = prefs[Keys.LAST_TARGET_SCORE_ANIMALS] ?: 15,
                    enableBGM = prefs[Keys.BGM_ENABLED] ?: true,
                    calmMode = prefs[Keys.CALM_MODE] ?: false,
                )
            }
        }
    }

    suspend fun saveLastConfig(config: GameConfig) {
        context.dataStore.edit { prefs ->
            when (config.mode) {
                GameMode.SHAPES -> {
                    prefs[Keys.LAST_SPAWN_INTERVAL_SHAPES] = config.spawnIntervalSeconds
                    prefs[Keys.LAST_HOLD_DURATION_SHAPES] = config.holdDurationSeconds
                    prefs[Keys.LAST_TARGET_SCORE_SHAPES] = config.targetScore
                }

                GameMode.ANIMALS -> {
                    prefs[Keys.LAST_SPAWN_INTERVAL_ANIMALS] = config.spawnIntervalSeconds
                    prefs[Keys.LAST_HOLD_DURATION_ANIMALS] = config.holdDurationSeconds
                    prefs[Keys.LAST_TARGET_SCORE_ANIMALS] = config.targetScore
                }
            }
            prefs[Keys.BGM_ENABLED] = config.enableBGM
            prefs[Keys.CALM_MODE] = config.calmMode
        }
    }

    // High scores per mode
    fun getHighScore(mode: GameMode): Flow<Int> = context.dataStore.data.map { prefs ->
        when (mode) {
            GameMode.SHAPES -> prefs[Keys.HIGH_SCORE_SHAPES] ?: 0
            GameMode.ANIMALS -> prefs[Keys.HIGH_SCORE_ANIMALS] ?: 0
        }
    }

    suspend fun updateHighScore(mode: GameMode, score: Int) {
        context.dataStore.edit { prefs ->
            val key = when (mode) {
                GameMode.SHAPES -> Keys.HIGH_SCORE_SHAPES
                GameMode.ANIMALS -> Keys.HIGH_SCORE_ANIMALS
            }
            val current = prefs[key] ?: 0
            if (score > current) {
                prefs[key] = score
            }
        }
    }

    suspend fun resetHighScore(mode: GameMode) {
        context.dataStore.edit { prefs ->
            val key = when (mode) {
                GameMode.SHAPES -> Keys.HIGH_SCORE_SHAPES
                GameMode.ANIMALS -> Keys.HIGH_SCORE_ANIMALS
            }
            prefs[key] = 0
        }
    }

    suspend fun setBgmEnabled(enabled: Boolean) {
        context.dataStore.edit { it[Keys.BGM_ENABLED] = enabled }
    }

    suspend fun setCalmMode(enabled: Boolean) {
        context.dataStore.edit { it[Keys.CALM_MODE] = enabled }
    }
}
