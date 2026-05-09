package com.kidstap.app.domain.model

sealed class GameState {
    object Idle : GameState()
    data class Active(
        val currentScore: Int = 0,
        val targetScore: Int = 20,
        val visibleItems: List<SpawnableItem> = emptyList(),
        val elapsedSeconds: Int = 0,
    ) : GameState()
    data class Finished(
        val finalScore: Int,
        val targetScore: Int,
        val isWin: Boolean,
    ) : GameState()
}
