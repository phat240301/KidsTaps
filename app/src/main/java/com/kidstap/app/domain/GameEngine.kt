package com.kidstap.app.domain

import com.kidstap.app.domain.model.AnimalType
import com.kidstap.app.domain.model.GameConfig
import com.kidstap.app.domain.model.GameState
import com.kidstap.app.domain.model.ShapeColors
import com.kidstap.app.domain.model.ShapeType
import com.kidstap.app.domain.model.SpawnableItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Core game engine:
 * - Spawns items (shapes or animals) at random positions
 * - Manages item lifecycle (spawn → hold → despawn)
 * - Tracks hits and scoring
 * - Emits game state updates
 */
class GameEngine(
    private val coroutineScope: CoroutineScope,
    private val config: GameConfig,
) {
    private val _gameState = MutableStateFlow<GameState>(GameState.Idle)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var isRunning = false
    private var score = 0
    private var visibleItems = mutableListOf<SpawnableItem>()
    private var elapsedSeconds = 0

    // Safe spawn area (leave margins from edges)
    private var gameAreaWidth = 400f
    private var gameAreaHeight = 700f
    private val marginPixels = 80f

    fun setGameAreaSize(width: Float, height: Float) {
        gameAreaWidth = width
        gameAreaHeight = height
    }

    fun start() {
        if (isRunning) return
        isRunning = true
        score = 0
        visibleItems.clear()
        elapsedSeconds = 0

        _gameState.value = GameState.Active(
            currentScore = 0,
            targetScore = config.targetScore,
            visibleItems = emptyList(),
        )

        // Start spawn loop
        coroutineScope.launch {
            spawnLoop()
        }

        // Start timer
        coroutineScope.launch {
            timerLoop()
        }
    }

    fun stop() {
        isRunning = false
    }

    fun onTap(x: Float, y: Float) {
        if (!isRunning || gameState.value !is GameState.Active) return

        var hitAny = false
        for (item in visibleItems.toList()) {
            if (item.isHit(x, y)) {
                hitAny = true
                visibleItems.remove(item)

                // Increment score
                score++

                // Check win condition
                if (score >= config.targetScore) {
                    finishGame(won = true)
                    return
                }

                // Emit audio event (caller will handle SFX)
                _tapCallback?.invoke(TapResult.Correct)

                break
            }
        }

        if (!hitAny) {
            _tapCallback?.invoke(TapResult.Wrong)
        }

        updateState()
    }

    // ─── Private ──────────────────────────────────────────────────────────
    private suspend fun spawnLoop() {
        while (isRunning) {
            delay((config.spawnIntervalSeconds * 1000).toLong())

            if (!isRunning) break

            // Spawn new item
            val item = createRandomItem()
            visibleItems.add(item)
            updateState()

            // Despawn after hold duration independently
            coroutineScope.launch {
                delay((config.holdDurationSeconds * 1000).toLong())
                if (visibleItems.contains(item)) {
                    visibleItems.remove(item)
                    updateState()
                }
            }
        }
    }

    private suspend fun timerLoop() {
        while (isRunning) {
            delay(1000)
            elapsedSeconds++
            updateState()
        }
    }

    private fun createRandomItem(): SpawnableItem {
        val x = Random.nextFloat() * (gameAreaWidth - 2 * marginPixels) + marginPixels
        val y = Random.nextFloat() * (gameAreaHeight - 2 * marginPixels) + marginPixels

        return when (config.mode) {
            com.kidstap.app.domain.model.GameMode.SHAPES -> {
                SpawnableItem.Shape(
                    type = ShapeType.random(),
                    color = ShapeColors.random(),
                    centerX = x,
                    centerY = y,
                    sizePixels = 100f,
                )
            }

            com.kidstap.app.domain.model.GameMode.ANIMALS -> {
                SpawnableItem.Animal(
                    type = AnimalType.randomCommon(),
                    centerX = x,
                    centerY = y,
                    sizePixels = 120f,
                )
            }
        }
    }

    private fun updateState() {
        if (!isRunning) return

        _gameState.value = GameState.Active(
            currentScore = score,
            targetScore = config.targetScore,
            visibleItems = visibleItems.toList(),
            elapsedSeconds = elapsedSeconds,
        )
    }

    private fun finishGame(won: Boolean) {
        isRunning = false
        _gameState.value = GameState.Finished(
            finalScore = score,
            targetScore = config.targetScore,
            isWin = won,
        )
    }

    // Audio callback
    private var _tapCallback: ((TapResult) -> Unit)? = null

    fun setTapCallback(callback: (TapResult) -> Unit) {
        _tapCallback = callback
    }

    enum class TapResult {
        Correct, Wrong
    }
}
