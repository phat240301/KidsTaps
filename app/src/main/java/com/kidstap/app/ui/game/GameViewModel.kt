package com.kidstap.app.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidstap.app.audio.SoundManager
import com.kidstap.app.data.SettingsRepository
import com.kidstap.app.domain.DifficultyCalculator
import com.kidstap.app.domain.GameEngine
import com.kidstap.app.domain.model.GameConfig
import com.kidstap.app.domain.model.GameMode
import com.kidstap.app.domain.model.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val soundManager: SoundManager,
) : ViewModel() {

    private val _gameState = MutableStateFlow<GameState>(GameState.Idle)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _gameConfig = MutableStateFlow<GameConfig?>(null)
    val gameConfig: StateFlow<GameConfig?> = _gameConfig.asStateFlow()

    private var gameEngine: GameEngine? = null

    init {
        viewModelScope.launch {
            // Load last config from repository
            // TODO: This should be passed from GameSetupScreen
            _gameConfig.value = GameConfig(
                mode = GameMode.SHAPES,
                childAge = 5,
                spawnIntervalSeconds = 4.0f,
                holdDurationSeconds = 4.5f,
                targetScore = 15,
                enableBGM = true,
                calmMode = false,
            )
        }
    }

    fun startGame(config: GameConfig, gameAreaWidth: Float, gameAreaHeight: Float) {
        _gameConfig.value = config

        gameEngine = GameEngine(viewModelScope, config).apply {
            setGameAreaSize(gameAreaWidth, gameAreaHeight)
            setTapCallback { result ->
                when (result) {
                    GameEngine.TapResult.Correct -> {
                        soundManager.playCorrect()
                    }
                    GameEngine.TapResult.Wrong -> {
                        soundManager.playWrong()
                    }
                }
            }
        }

        // Collect game state from engine
        viewModelScope.launch {
            gameEngine!!.gameState.collect { state ->
                _gameState.value = state

                if (state is GameState.Finished && state.isWin) {
                    soundManager.playWin()
                    soundManager.stopBGM()
                    // Save high score
                    viewModelScope.launch {
                        settingsRepository.updateHighScore(config.mode, state.finalScore)
                    }
                }
            }
        }

        // Start BGM if enabled
        if (config.enableBGM) {
            soundManager.playBGM()
        }

        // Start game engine
        gameEngine!!.start()
    }

    fun onGameAreaTap(x: Float, y: Float) {
        gameEngine?.onTap(x, y)
    }

    fun endGame() {
        gameEngine?.stop()
        soundManager.stopBGM()
    }

    override fun onCleared() {
        super.onCleared()
        gameEngine?.stop()
        soundManager.stopBGM()
    }
}
